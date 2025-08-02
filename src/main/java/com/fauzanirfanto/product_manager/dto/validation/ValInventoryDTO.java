package com.fauzanirfanto.product_manager.dto.validation;

import com.fauzanirfanto.product_manager.dto.relation.RelProductDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelWarehouseDTO;
import com.fauzanirfanto.product_manager.dto.response.RespProductCategoryDTO;
import com.fauzanirfanto.product_manager.utility.ConstantsMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValInventoryDTO {

    @NotNull(message = ConstantsMessage.VAL_RELATION)
    private RelProductDTO product;

    @NotNull(message = ConstantsMessage.VAL_RELATION)
    private RelWarehouseDTO warehouse;

    @Min(value = 0, message = ConstantsMessage.VAL_NUMBER)
    private Integer stock;

    public RelProductDTO getProduct() {
        return product;
    }

    public void setProduct(RelProductDTO product) {
        this.product = product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public RelWarehouseDTO getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(RelWarehouseDTO warehouse) {
        this.warehouse = warehouse;
    }
}
