package com.fauzanirfanto.product_manager.dto.validation;

import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
import com.fauzanirfanto.product_manager.utility.ConstantsMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValWarehouseDTO {

    @Pattern(regexp = "^[\\w\\s]{3,50}$", message = ConstantsMessage.VAL_NAMA)
    private String nama;

    @Pattern(regexp = "^[\\w\\s]{3,50}$", message = ConstantsMessage.VAL_NAMA)
    private String alamat;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
