package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.Product;
import com.fauzanirfanto.product_manager.model.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

    /** Select * From MstGroupMenu WHERE toLower(Email) Like toLower('%chihuy%') */
    public Page<Warehouse> findByNamaContainsIgnoreCaseAndIsDeletedFalse(String nama, Pageable pageable);

    /** Ini untuk Report */
    public List<Warehouse> findByNamaContainsIgnoreCaseAndIsDeletedFalse(String nama);

    public Page<Warehouse> findAllByIsDeletedFalse(Pageable pageable);
    public List<Warehouse> findAllByIsDeletedFalse();

    /** Select * From MstGroupMenu WHERE toLower(Email) Like toLower('%chihuy%') */
    public Page<Warehouse> findByAlamatContainsIgnoreCaseAndIsDeletedFalse(String alamat, Pageable pageable);

    /** Ini untuk Report */
    public List<Warehouse> findByAlamatContainsIgnoreCaseAndIsDeletedFalse(String alamat);

}
