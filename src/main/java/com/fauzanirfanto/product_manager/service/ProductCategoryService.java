package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductCategoryDTO;
import com.fauzanirfanto.product_manager.handler.GlobalResponse;
import com.fauzanirfanto.product_manager.model.ProductCategory;
import com.fauzanirfanto.product_manager.repositories.ProductCategoryRepo;
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
import java.util.Map;
import java.util.Optional;

/**
 *  Platform Code  - PM
 *  Modul Code - 01
 *  FV - FE
 */

@Service
@Transactional
public class ProductCategoryService implements IService<ProductCategory> {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(id);
            if (!optionalProductCategory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM01FV001",request);
            }
            productCategoryRepo.deleteById(id);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","delete(Long id, HttpServletRequest request) -- Line 117 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDihapus("PM01FE002",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> save(ProductCategory productCategory, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (productCategory == null) {
            return GlobalResponse.dataTidakValid("PM01FV011",request);
        }

        try {
            List<ProductCategory> listProductCategory = productCategoryRepo.findByNamaContainsIgnoreCase(productCategory.getNama());

            if (!listProductCategory.isEmpty()) {
                return GlobalResponse.dataHarusUnique("PM01FV012",request);
            }
//            productCategory.setCreatedBy(Long.parseLong(mapToken.get("userId").toString()));
            productCategoryRepo.save(productCategory);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","save(ProductCategory productCategory, HttpServletRequest request) -- Line 69 "+ RequestCapture.allRequest(request),e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("PM01FE013",request);

        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, ProductCategory productCategory, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (productCategory == null) {
            return GlobalResponse.dataTidakValid("PM01FV021",request);
        }

        try {
            Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(id);
            if (!optionalProductCategory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM01FV022",request);
            }
            List<ProductCategory> listProductCategory = productCategoryRepo.findByNamaContainsIgnoreCase(productCategory.getNama());

            if (!listProductCategory.isEmpty()) {
                return GlobalResponse.dataHarusUnique("PM01FV023",request);
            }
            LocalDateTime now = LocalDateTime.now();

            ProductCategory nextProductCategory = optionalProductCategory.get();
//            nextProductCategory.setModifiedBy(Long.parseLong(mapToken.get("userId").toString()));
            nextProductCategory.setModifiedDate(now);
            nextProductCategory.setNama(productCategory.getNama());

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","update(Long id, ProductCategory productCategory, HttpServletRequest request) -- Line 102 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("PM01FE024",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<ProductCategory> page = null;
        List<ProductCategory> list = null;
        page = productCategoryRepo.findAll(pageable);
        list = page.getContent();
        List<RespProductCategoryDTO> lt = convertToRespProductCategoryDTO(list);

        if (lt.isEmpty()) {
            lt.add(new RespProductCategoryDTO());
        }

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,null,null),
                request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<ProductCategory> optionalProductCategory = null;
        try {
            optionalProductCategory = productCategoryRepo.findById(id);
            if (!optionalProductCategory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM01FV041",request);
            }
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","findById(Long id, HttpServletRequest request) -- Line 144 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.terjadiKesalahan("KPL01FE042",request);
        }
        return GlobalResponse.dataDitemukan(modelMapper.map(optionalProductCategory.get(),RespProductCategoryDTO.class),request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<ProductCategory> page = null;
        List<ProductCategory> list = null;
        switch (columnName) {
            case "nama": page = productCategoryRepo.findByNamaContainsIgnoreCase(value,pageable);break;
            default: page = productCategoryRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespProductCategoryDTO> lt = convertToRespProductCategoryDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,columnName,value),
                request);
    }

    public List<RespProductCategoryDTO> convertToRespProductCategoryDTO (List<ProductCategory> productCategories) {
        List<RespProductCategoryDTO> respProductCategoryDTOList = modelMapper.map(productCategories, new TypeToken<List<RespProductCategoryDTO>>() {}.getType());
        return respProductCategoryDTOList;
    }

    public ProductCategory convertToEntity (ValProductCategoryDTO valProductCategoryDTO) {
        ProductCategory productCategory = modelMapper.map(valProductCategoryDTO, ProductCategory.class);
        return productCategory;
    }

}
