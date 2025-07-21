package com.fauzanirfanto.product_manager.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MstWarehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDWarehouse")
    private Long id;

    @Column(name = "Nama", nullable = false, length = 50)
    private String nama;

    @Column(name = "Alamat", nullable = false, length = 50)
    private String alamat;

    @Column(name = "IsDeleted",columnDefinition = ("bit default 0"))
    private Boolean isDeleted=false;

//    @OneToMany(mappedBy = "warehouseIn", orphanRemoval = false)
//    private List<Transfer> warehouseInTransfers;
//
//    @OneToMany(mappedBy = "warehouseOut", orphanRemoval = false)
//    private List<Transfer> warehouseOutTransfers;

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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    //    public List<Transfer> getWarehouseInTransfers() {
//        return warehouseInTransfers;
//    }
//
//    public void setWarehouseInTransfers(List<Transfer> warehouseInTransfers) {
//        this.warehouseInTransfers = warehouseInTransfers;
//    }
//
//    public List<Transfer> getWarehouseOutTransfers() {
//        return warehouseOutTransfers;
//    }
//
//    public void setWarehouseOutTransfers(List<Transfer> warehouseOutTransfers) {
//        this.warehouseOutTransfers = warehouseOutTransfers;
//    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
