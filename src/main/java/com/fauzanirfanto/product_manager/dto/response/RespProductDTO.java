package com.fauzanirfanto.product_manager.dto.response;

import com.fauzanirfanto.product_manager.dto.relation.RelProductCategoryDTO;
import com.fauzanirfanto.product_manager.model.ProductCategory;

public class RespProductDTO {

    private Long id;

    private String nama;

    private RelProductCategoryDTO productCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public RelProductCategoryDTO getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(RelProductCategoryDTO productCategory) {
        this.productCategory = productCategory;
    }
}
