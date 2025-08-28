package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespInventoryDTO;
import com.fauzanirfanto.product_manager.dto.response.RespProductDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValInventoryDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductDTO;
import com.fauzanirfanto.product_manager.handler.GlobalResponse;
import com.fauzanirfanto.product_manager.model.Inventory;
import com.fauzanirfanto.product_manager.model.Product;
import com.fauzanirfanto.product_manager.model.ProductCategory;
import com.fauzanirfanto.product_manager.model.Warehouse;
import com.fauzanirfanto.product_manager.repositories.InventoryRepo;
import com.fauzanirfanto.product_manager.repositories.ProductRepo;
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
 *  Modul Code - 04
 *  FV - FE
 */

@Service
@Transactional
public class InventoryService implements IService<Inventory> {

    @Autowired
    private InventoryRepo inventoryRepo;
    
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private WarehouseRepo warehouseRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Inventory> optionalInventory = inventoryRepo.findById(id);
            if (!optionalInventory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV001",request);
            }
            inventoryRepo.deleteById(id);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","delete(Long id, HttpServletRequest request) -- Line 117 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDihapus("PM04FE002",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> save(Inventory inventory, HttpServletRequest request) {
        if (inventory == null) {
            return GlobalResponse.dataTidakValid("PM04FV011",request);
        }

        try {
            Optional<Product> optionalProduct = productRepo.findById(inventory.getProduct().getId());
            if (!optionalProduct.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV012",request);
            }

            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(inventory.getWarehouse().getId());
            if (!optionalWarehouse.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV013",request);
            }

            Optional<Inventory> optionalInventory = inventoryRepo.findByProductIdAndWarehouseId(inventory.getProduct().getId(), inventory.getWarehouse().getId());
            if (optionalInventory.isPresent()) {
                return GlobalResponse.dataHarusUnique("PM04FV014",request);
            }

            inventoryRepo.save(inventory);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","save(ProductCategory productCategory, HttpServletRequest request) -- Line 69 "+ RequestCapture.allRequest(request),e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("PM04FE015",request);

        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Inventory inventory, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (inventory == null) {
            return GlobalResponse.dataTidakValid("PM04FV021",request);
        }

        try {
            Optional<Inventory> optionalInventory = inventoryRepo.findById(id);
            if (!optionalInventory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV022",request);
            }

            Optional<Product> optionalProduct = productRepo.findById(inventory.getProduct().getId());
            if (!optionalProduct.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV023",request);
            }

            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(inventory.getWarehouse().getId());
            if (!optionalWarehouse.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV024",request);
            }

            Optional<Inventory> optionalInventoryUnique = inventoryRepo.findByProductIdAndWarehouseId(inventory.getProduct().getId(), inventory.getWarehouse().getId());

            if (optionalInventoryUnique.isPresent()) {
                Inventory nextInventoryUnique = optionalInventoryUnique.get();

                if (!nextInventoryUnique.getId().equals(id)) {
                    return GlobalResponse.dataHarusUnique("PM04FV025",request);
                }
            }

            LocalDateTime now = LocalDateTime.now();

            Inventory nextInventory = optionalInventory.get();

//            nextProductCategory.setModifiedBy(Long.parseLong(mapToken.get("userId").toString()));
            nextInventory.setModifiedDate(now);
            nextInventory.setProduct(inventory.getProduct());
            nextInventory.setWarehouse(inventory.getWarehouse());
            nextInventory.setStock(inventory.getStock());

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","update(Long id, ProductCategory productCategory, HttpServletRequest request) -- Line 102 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("PM04FE026",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Inventory> page = null;
        List<Inventory> list = null;
        page = inventoryRepo.findAll(pageable);
        list = page.getContent();
        List<RespInventoryDTO> lt = convertToRespInventoryDTO(list);

        if (lt.isEmpty()) {
            lt.add(new RespInventoryDTO());
        }

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,null,null),
                request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Inventory> optionalInventory = null;
        try {
            optionalInventory = inventoryRepo.findById(id);
            if (!optionalInventory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM04FV041",request);
            }
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","findById(Long id, HttpServletRequest request) -- Line 144 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.terjadiKesalahan("PM04FE042",request);
        }
        return GlobalResponse.dataDitemukan(modelMapper.map(optionalInventory.get(),RespInventoryDTO.class),request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Inventory> page = null;
        List<Inventory> list = null;
        switch (columnName) {
            case "product": page = inventoryRepo.cariProduct(value,pageable);break;
            case "warehouse": page = inventoryRepo.cariWarehouse(value,pageable);break;
            case "stock":
                try {
                    Integer stockValue = Integer.parseInt(value);
                    page = inventoryRepo.findByStock(stockValue, pageable);
                } catch (Exception e) {
                    return GlobalResponse.dataTidakValid("PM04FV051",request);
                }
                break;
            default: page = inventoryRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespInventoryDTO> lt = convertToRespInventoryDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,columnName,value),
                request);
    }

    public List<RespInventoryDTO> convertToRespInventoryDTO (List<Inventory> inventories) {
        List<RespInventoryDTO> respInventoryDTOList = modelMapper.map(inventories, new TypeToken<List<RespInventoryDTO>>() {}.getType());
        return respInventoryDTOList;
    }

    public Inventory convertToEntity (ValInventoryDTO valInventoryDTO) {
        Inventory inventory = modelMapper.map(valInventoryDTO, Inventory.class);
        return inventory;
    }

}
