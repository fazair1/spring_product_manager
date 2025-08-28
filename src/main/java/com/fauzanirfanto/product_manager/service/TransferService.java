package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespInventoryDTO;
import com.fauzanirfanto.product_manager.dto.response.RespTransferDTO;
import com.fauzanirfanto.product_manager.handler.GlobalResponse;
import com.fauzanirfanto.product_manager.model.*;
import com.fauzanirfanto.product_manager.repositories.InventoryRepo;
import com.fauzanirfanto.product_manager.repositories.StatusRepo;
import com.fauzanirfanto.product_manager.repositories.TransferRepo;
import com.fauzanirfanto.product_manager.repositories.WarehouseRepo;
import com.fauzanirfanto.product_manager.utility.TransformPagination;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *  Platform Code  - PM
 *  Modul Code - 05
 *  FV - FE
 */

@Service
@Transactional
public class TransferService implements IService<Transfer> {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private TransferRepo transferRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Transfer> optionalTransfer = transferRepo.findById(id);
            if (!optionalTransfer.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV001",request);
            }
            transferRepo.deleteById(id);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","delete(Long id, HttpServletRequest request) -- Line 117 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDihapus("PM05FE002",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> save(Transfer transfer, HttpServletRequest request) {
        if (transfer == null) {
            return GlobalResponse.dataTidakValid("PM05FV011",request);
        }

        try {
            Optional<Inventory> optionalDatabaseInventory = inventoryRepo.findById(transfer.getInventory().getId());
            if (!optionalDatabaseInventory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV012",request);
            }

            Optional<Warehouse> optionalDatabaseWarehouseFrom = warehouseRepo.findById(transfer.getWarehouseFrom().getId());
            if (!optionalDatabaseWarehouseFrom.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV013",request);
            }

            Optional<Warehouse> optionalDatabaseWarehouseTo = warehouseRepo.findById(transfer.getWarehouseTo().getId());
            if (!optionalDatabaseWarehouseTo.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV014",request);
            }

            Inventory databaseInventory = optionalDatabaseInventory.get();

            if (transfer.getStock() > databaseInventory.getStock()) {
                return GlobalResponse.dataTidakValid("PM05FV015",request);
            }

            Optional<Status> optionalStatus = statusRepo.findById(1L);
            Status nextStatus = optionalStatus.get();
            transfer.setWarehouseFrom(databaseInventory.getWarehouse());
            transfer.setStatus(nextStatus);

            transferRepo.save(transfer);

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","save(ProductCategory productCategory, HttpServletRequest request) -- Line 69 "+ RequestCapture.allRequest(request),e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("PM04FE016",request);

        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Transfer inputTransfer, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (inputTransfer == null) {
            return GlobalResponse.dataTidakValid("PM05FV021",request);
        }

        try {
            Optional<Transfer> optionalDatabaseTransfer = transferRepo.findById(id);
            if (!optionalDatabaseTransfer.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV022",request);
            }

            Optional<Status> optionalStatus = statusRepo.findById(inputTransfer.getStatus().getId());
            if (!optionalStatus.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM05FV023",request);
            }

            Transfer databaseTransfer = optionalDatabaseTransfer.get();

            if (!databaseTransfer.getStatus().getNama().equals("Cancelled") || !databaseTransfer.getStatus().getNama().equals("Done")) {
                LocalDateTime now = LocalDateTime.now();
                Optional<Inventory> optionalDatabaseInventory = inventoryRepo.findById(databaseTransfer.getInventory().getId());
                Inventory databaseInventory = optionalDatabaseInventory.get();

                if (inputTransfer.getStatus().getNama().equals("In Progress")) {

                    if (inputTransfer.getStock() > databaseInventory.getStock()) {
                        return GlobalResponse.dataTidakValid("PM05FV024", request);
                    }
                    databaseTransfer.setStock(inputTransfer.getStock());
                }
                else if (inputTransfer.getStatus().getNama().equals("Done")) {

                    if (inputTransfer.getStock() > databaseInventory.getStock()) {
                        return GlobalResponse.dataTidakValid("PM05FV025",request);
                    }
                    databaseTransfer.setStock(inputTransfer.getStock());
                    databaseTransfer.setStatus(inputTransfer.getStatus());

                    Optional<Inventory> optionalDestinationInventory = inventoryRepo.findByProductIdAndWarehouseId(databaseInventory.getProduct().getId(), databaseTransfer.getWarehouseTo().getId());
                    if (optionalDestinationInventory.isPresent()) {
                        Inventory destinationInventory = optionalDestinationInventory.get();
                        destinationInventory.setStock((destinationInventory.getStock()) + databaseTransfer.getStock());
                    }
                    else {
                        Inventory newDestinationInventory = new Inventory();
                        newDestinationInventory.setProduct(databaseInventory.getProduct());
                        newDestinationInventory.setWarehouse(databaseTransfer.getWarehouseTo());
                        newDestinationInventory.setStock(databaseTransfer.getStock());
                        inventoryRepo.save(newDestinationInventory);
                    }
                    databaseInventory.setStock((databaseInventory.getStock()) - databaseTransfer.getStock());
                }
                databaseTransfer.setModifiedDate(now);
            }
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","update(Long id, ProductCategory productCategory, HttpServletRequest request) -- Line 102 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("PM04FE026",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Transfer> page = null;
        List<Transfer> list = null;
        page = transferRepo.findAll(pageable);
        list = page.getContent();
        List<RespTransferDTO> lt = convertToRespTransferDTO(list);

        if (lt.isEmpty()) {
            lt.add(new RespTransferDTO());
        }

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,null,null),
                request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Transfer> optionalTransfer = null;
        try {
            optionalTransfer = transferRepo.findById(id);
            if (!optionalTransfer.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV041",request);
            }
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","findById(Long id, HttpServletRequest request) -- Line 144 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.terjadiKesalahan("PM04FE042",request);
        }
        return GlobalResponse.dataDitemukan(modelMapper.map(optionalTransfer.get(),RespTransferDTO.class),request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Transfer> page = null;
        List<Transfer> list = null;
        switch (columnName) {
            case "product": page = transferRepo.cariProductTransfer(value,pageable);break;
            case "warehouseFrom": page = transferRepo.cariWarehouseFromTransfer(value,pageable);break;
            case "warehouseTo": page = transferRepo.cariWarehouseToTransfer(value,pageable);break;
            case "stock":
                try {
                    Integer stockValue = Integer.parseInt(value);
                    page = transferRepo.findByStock(stockValue, pageable);
                } catch (Exception e) {
                    return GlobalResponse.dataTidakValid("PM04FV051",request);
                }
                break;
            default: page = transferRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespTransferDTO> lt = convertToRespTransferDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,columnName,value),
                request);
    }

    public List<RespTransferDTO> convertToRespTransferDTO (List<Transfer> transfers) {
        List<RespTransferDTO> respTransferDTOList = modelMapper.map(transfers, new TypeToken<List<RespTransferDTO>>() {}.getType());
        return respTransferDTOList;
    }

}
