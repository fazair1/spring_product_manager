package com.fauzanirfanto.product_manager.dto.validation;

import com.fauzanirfanto.product_manager.utility.ConstantsMessage;
import jakarta.validation.constraints.Pattern;

public class ValProductCategoryDTO {
    @Pattern(regexp = "^[\\w\\s]{3,50}$", message = ConstantsMessage.VAL_NAMA)
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
