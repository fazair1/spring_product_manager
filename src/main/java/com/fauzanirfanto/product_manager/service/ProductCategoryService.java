package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
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

import java.util.List;

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
        return null;
    }

    @Override
    public ResponseEntity<Object> save(ProductCategory productCategory, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, ProductCategory productCategory, HttpServletRequest request) {
        return null;
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
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    public List<RespProductCategoryDTO> convertToRespProductCategoryDTO (List<ProductCategory> productCategories) {
        List<RespProductCategoryDTO> respProductCategoryDTOList = modelMapper.map(productCategories, new TypeToken<List<RespProductCategoryDTO>>() {}.getType());
        return respProductCategoryDTOList;
    }

}
