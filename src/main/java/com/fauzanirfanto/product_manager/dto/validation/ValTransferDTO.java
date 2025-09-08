package com.fauzanirfanto.product_manager.dto.validation;

import com.fauzanirfanto.product_manager.dto.relation.RelInventoryDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelStatusDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelWarehouseDTO;
import com.fauzanirfanto.product_manager.model.Inventory;
import com.fauzanirfanto.product_manager.model.Status;
import com.fauzanirfanto.product_manager.model.Warehouse;
import com.fauzanirfanto.product_manager.utility.ConstantsMessage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ValTransferDTO {

    @NotNull(message = ConstantsMessage.VAL_RELATION)
    private RelInventoryDTO inventory;

    private RelWarehouseDTO warehouseFrom;

    @NotNull(message = ConstantsMessage.VAL_RELATION)
    private RelWarehouseDTO warehouseTo;

    @NotNull(message = ConstantsMessage.VAL_NOT_NULL) // if stock must not be null
    @Min(value = 0, message = "Stock harus angka nol atau positive!")
    private Integer stock;

    private RelStatusDTO status;

    public RelStatusDTO getStatus() {
        return status;
    }

    public void setStatus(RelStatusDTO status) {
        this.status = status;
    }

    public RelInventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(RelInventoryDTO inventory) {
        this.inventory = inventory;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public RelWarehouseDTO getWarehouseFrom() {
        return warehouseFrom;
    }

    public void setWarehouseFrom(RelWarehouseDTO warehouseFrom) {
        this.warehouseFrom = warehouseFrom;
    }

    public RelWarehouseDTO getWarehouseTo() {
        return warehouseTo;
    }

    public void setWarehouseTo(RelWarehouseDTO warehouseTo) {
        this.warehouseTo = warehouseTo;
    }
}
