package com.fauzanirfanto.product_manager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "MstTransfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTransfer")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDProduct", foreignKey = @ForeignKey(name = "fk-transfer-to-product"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "IDWarehouseIn", foreignKey = @ForeignKey(name = "fk-to-warehouseIn"))
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Warehouse warehouseIn;

    @ManyToOne
    @JoinColumn(name = "IDWarehouseOut", foreignKey = @ForeignKey(name = "fk-to-warehouseOut"))
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Warehouse warehouseOut;

    @Column(name = "Stock")
    private Integer stock = 0;

    @ManyToOne
    @JoinColumn(name = "IDStatus", foreignKey = @ForeignKey(name = "fk-to-status"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Status status;

    @Column(name = "CreatedBy",updatable = false, nullable = false)
    private Long createdBy = 1L;

    @Column(name = "CreatedDate",updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "ModifiedBy", insertable = false)
    private Long modifiedBy;

    @Column(name = "ModifiedDate", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Warehouse getWarehouseIn() {
        return warehouseIn;
    }

    public void setWarehouseIn(Warehouse warehouseIn) {
        this.warehouseIn = warehouseIn;
    }

    public Warehouse getWarehouseOut() {
        return warehouseOut;
    }

    public void setWarehouseOut(Warehouse warehouseOut) {
        this.warehouseOut = warehouseOut;
    }
}
