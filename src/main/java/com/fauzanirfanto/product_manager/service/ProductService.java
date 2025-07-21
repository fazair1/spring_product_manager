package com.fauzanirfanto.product_manager.service;

import com.fauzanirfanto.product_manager.core.IService;
import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
import com.fauzanirfanto.product_manager.dto.response.RespProductDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductCategoryDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductDTO;
import com.fauzanirfanto.product_manager.handler.GlobalResponse;
import com.fauzanirfanto.product_manager.model.Product;
import com.fauzanirfanto.product_manager.model.ProductCategory;
import com.fauzanirfanto.product_manager.repositories.ProductCategoryRepo;
import com.fauzanirfanto.product_manager.repositories.ProductRepo;
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
 *  Modul Code - 02
 *  FV - FE
 */

@Service
@Transactional
public class ProductService implements IService<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransformPagination transformPagination;

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if (!optionalProduct.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM02FV001",request);
            }
            productRepo.deleteById(id);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","delete(Long id, HttpServletRequest request) -- Line 117 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDihapus("PM02FE002",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }

    @Override
    public ResponseEntity<Object> save(Product product, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (product == null) {
            return GlobalResponse.dataTidakValid("PM02FV011",request);
        }

        try {
            Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(product.getProductCategory().getId());
            if (!optionalProductCategory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("KPL02FV012",request);
            }

            productRepo.save(product);
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","save(ProductCategory productCategory, HttpServletRequest request) -- Line 69 "+ RequestCapture.allRequest(request),e, OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDisimpan("PM02FE013",request);

        }
        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    public ResponseEntity<Object> update(Long id, Product product, HttpServletRequest request) {
//        Map<String,Object> mapToken = GlobalFunction.extractToken(request);

        if (product == null) {
            return GlobalResponse.dataTidakValid("PM02FV021",request);
        }

        try {
            Optional<Product> optionalProduct = productRepo.findById(id);
            if (!optionalProduct.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM02FV022",request);
            }

            Optional<ProductCategory> optionalProductCategory = productCategoryRepo.findById(product.getProductCategory().getId());
            if (!optionalProductCategory.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM02FV023",request);
            }

            LocalDateTime now = LocalDateTime.now();

            Product nextProduct = optionalProduct.get();

//            nextProductCategory.setModifiedBy(Long.parseLong(mapToken.get("userId").toString()));
            nextProduct.setModifiedDate(now);
            nextProduct.setNama(product.getNama());
            nextProduct.setProductCategory(product.getProductCategory());

        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","update(Long id, ProductCategory productCategory, HttpServletRequest request) -- Line 102 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.dataGagalDiubah("PM02FE024",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        Page<Product> page = null;
        List<Product> list = null;
        page = productRepo.findAll(pageable);
        list = page.getContent();
        List<RespProductDTO> lt = convertToRespProductDTO(list);

        if (lt.isEmpty()) {
            lt.add(new RespProductDTO());
        }

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,null,null),
                request);
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        Optional<Product> optionalProduct = null;
        try {
            optionalProduct = productRepo.findById(id);
            if (!optionalProduct.isPresent()) {
                return GlobalResponse.dataTidakDitemukan("PM02FV041",request);
            }
        }catch (Exception e) {
//            LoggingFile.logException("ProductCategoryService","findById(Long id, HttpServletRequest request) -- Line 144 "+RequestCapture.allRequest(request),e,OtherConfig.getEnableLog());
            return GlobalResponse.terjadiKesalahan("PM02FE042",request);
        }
        return GlobalResponse.dataDitemukan(modelMapper.map(optionalProduct.get(),RespProductDTO.class),request);
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        Page<Product> page = null;
        List<Product> list = null;
        switch (columnName) {
            case "nama": page = productRepo.findByNamaContainsIgnoreCase(value,pageable);break;
            case "category": page = productRepo.cariCategory(value,pageable);break;
            default: page = productRepo.findAll(pageable);
        }
        list = page.getContent();
        List<RespProductDTO> lt = convertToRespProductDTO(list);

        return GlobalResponse.dataDitemukan(transformPagination.transformPagination(lt,page,columnName,value),
                request);
    }

    public List<RespProductDTO> convertToRespProductDTO (List<Product> products) {
        List<RespProductDTO> respProductDTOList = modelMapper.map(products, new TypeToken<List<RespProductDTO>>() {}.getType());
        return respProductDTOList;
    }

    public Product convertToEntity (ValProductDTO valProductDTO) {
        Product product = modelMapper.map(valProductDTO, Product.class);
        return product;
    }

}
