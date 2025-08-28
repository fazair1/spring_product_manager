package com.fauzanirfanto.product_manager.dto.response;

import com.fauzanirfanto.product_manager.dto.relation.RelInventoryDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelStatusDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelWarehouseDTO;
import com.fauzanirfanto.product_manager.model.Inventory;
import com.fauzanirfanto.product_manager.model.Status;
import com.fauzanirfanto.product_manager.model.Warehouse;

public class RespTransferDTO {

    private Long id;

    private RelInventoryDTO inventory;

    private RelWarehouseDTO warehouseFrom;

    private RelWarehouseDTO warehouseTo;

    private Integer stock;

    private RelStatusDTO status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelInventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(RelInventoryDTO inventory) {
        this.inventory = inventory;
    }

    public RelStatusDTO getStatus() {
        return status;
    }

    public void setStatus(RelStatusDTO status) {
        this.status = status;
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
