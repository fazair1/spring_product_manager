package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespProductDTO;
import com.fauzanirfanto.product_manager.dto.response.RespWarehouseDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValWarehouseDTO;
import com.fauzanirfanto.product_manager.handler.GlobalResponse;
import com.fauzanirfanto.product_manager.model.Product;
import com.fauzanirfanto.product_manager.model.ProductCategory;
import com.fauzanirfanto.product_manager.model.Warehouse;
import com.fauzanirfanto.product_manager.repositories.ProductCategoryRepo;
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
 *  Modul Code - 03
 *  FV - FE
 */

@Service
@Transactional
public class WarehouseService implements IService<Warehouse> {

    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
            if (!optionalWarehouse.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM03FV001",request);
            }
            Warehouse nextWarehouse = optionalWarehouse.get();
            LocalDateTime now = LocalDateTime.now();

//            nextWarehouse.setModifiedBy(Long.parseLong(mapToken.get("userId").toString()));
            nextWarehouse.setModifiedDate(now);
            nextWarehouse.setDeleted(true);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","delete(Long id, HttpServletRequest request) -- Line 117 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDihapus("PM03FE002",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> save(Warehouse warehouse, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (warehouse == null) {
            return GlobalResponse.dataTidakValid("PM03FV011",request);
        }

        try {
            warehouseRepo.save(warehouse);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","save(ProductCategory productCategory, HttpServletRequest request) -- Line 69 "+ RequestCapture.allRequest(request),e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("PM03FE012",request);

        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Warehouse warehouse, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (warehouse == null) {
            return GlobalResponse.dataTidakValid("PM03FV021",request);
        }

        try {
            Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(id);
            if (!optionalWarehouse.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM03FV022",request);
            }

            LocalDateTime now = LocalDateTime.now();

            Warehouse nextWarehouse = optionalWarehouse.get();

//            nextProductCategory.setModifiedBy(Long.parseLong(mapToken.get("userId").toString()));
            nextWarehouse.setModifiedDate(now);
            nextWarehouse.setNama(warehouse.getNama());
            nextWarehouse.setAlamat(warehouse.getAlamat());

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","update(Long id, ProductCategory productCategory, HttpServletRequest request) -- Line 102 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("PM03FE023",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Warehouse> page = null;
        List<Warehouse> list = null;
        page = warehouseRepo.findAllByIsDeletedFalse(pageable);
        list = page.getContent();
        List<RespWarehouseDTO> lt = convertToRespWarehouseDTO(list);

        if (lt.isEmpty()) {
            lt.add(new RespWarehouseDTO());
        }

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,null,null),
                request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Warehouse> optionalWarehouse = null;
        try {
            optionalWarehouse = warehouseRepo.findById(id);
            if (!optionalWarehouse.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM03FV041",request);
            }
            Warehouse nextWarehouse = optionalWarehouse.get();

            if (nextWarehouse.getDeleted().equals(true)) {
                return GlobalResponse.dataTidakDitemukan("PM03FV042",request);
            }

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","findById(Long id, HttpServletRequest request) -- Line 144 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.terjadiKesalahan("PM03FE043",request);
        }
        return GlobalResponse.dataDitemukan(modelMapper.map(optionalWarehouse.get(),RespWarehouseDTO.class),request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Warehouse> page = null;
        List<Warehouse> list = null;
        switch (columnName) {
            case "nama": page = warehouseRepo.findByNamaContainsIgnoreCaseAndIsDeletedFalse(value,pageable);break;
            case "alamat": page = warehouseRepo.findByAlamatContainsIgnoreCaseAndIsDeletedFalse(value,pageable);break;
            default: page = warehouseRepo.findAllByIsDeletedFalse(pageable);
        }
        list = page.getContent();
        List<RespWarehouseDTO> lt = convertToRespWarehouseDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,columnName,value),
                request);
    }

    public List<RespWarehouseDTO> convertToRespWarehouseDTO (List<Warehouse> warehouses) {
        List<RespWarehouseDTO> respWarehouseDTOList = modelMapper.map(warehouses, new TypeToken<List<RespWarehouseDTO>>() {}.getType());
        return respWarehouseDTOList;
    }

    public Warehouse convertToEntity (ValWarehouseDTO valWarehouseDTO) {
        Warehouse warehouse = modelMapper.map(valWarehouseDTO, Warehouse.class);
        return warehouse;
    }

}
