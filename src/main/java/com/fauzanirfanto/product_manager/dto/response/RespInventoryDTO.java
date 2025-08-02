package com.fauzanirfanto.product_manager.dto.response;

import com.fauzanirfanto.product_manager.dto.relation.RelProductDTO;
import com.fauzanirfanto.product_manager.dto.relation.RelWarehouseDTO;
import com.fauzanirfanto.product_manager.model.Product;
import com.fauzanirfanto.product_manager.model.Warehouse;

public class RespInventoryDTO {

    private Long id;

    private RelProductDTO product;

    private RelWarehouseDTO warehouse;

    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
