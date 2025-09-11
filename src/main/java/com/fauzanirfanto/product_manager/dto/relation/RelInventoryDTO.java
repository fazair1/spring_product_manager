package com.fauzanirfanto.product_manager.dto.relation;

public class RelInventoryDTO {

    private Long id;

    private RelProductDTO product;

    public RelProductDTO getProduct() {
        return product;
    }

    public void setProduct(RelProductDTO product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
