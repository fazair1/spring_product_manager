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
    @JoinColumn(name = "IDInventory", foreignKey = @ForeignKey(name = "fk-transfer-to-inventory"))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "IDWarehouseFrom", foreignKey = @ForeignKey(name = "fk-to-warehouseFrom"))
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Warehouse warehouseFrom;

    @ManyToOne
    @JoinColumn(name = "IDWarehouseTo", foreignKey = @ForeignKey(name = "fk-to-IDWarehouseTo"))
//    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Warehouse warehouseTo;

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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Warehouse getWarehouseFrom() {
        return warehouseFrom;
    }

    public void setWarehouseFrom(Warehouse warehouseFrom) {
        this.warehouseFrom = warehouseFrom;
    }

    public Warehouse getWarehouseTo() {
        return warehouseTo;
    }

    public void setWarehouseTo(Warehouse warehouseTo) {
        this.warehouseTo = warehouseTo;
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
}
