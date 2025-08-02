package com.fauzanirfanto.product_manager.dto.validation;

import com.fauzanirfanto.product_manager.dto.relation.RelProductCategoryDTO;
import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
import com.fauzanirfanto.product_manager.utility.ConstantsMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValProductDTO {

    @Pattern(regexp = "^[\\w\\s]{3,50}$", message = ConstantsMessage.VAL_NAMA)
    private String nama;

    @NotNull(message = ConstantsMessage.VAL_RELATION)
    private RelProductCategoryDTO productCategory;

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
