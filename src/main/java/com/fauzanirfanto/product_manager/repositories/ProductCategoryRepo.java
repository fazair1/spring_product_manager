package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    /** Select * From MstGroupMenu WHERE toLower(Email) Like toLower('%chihuy%') */
    public Page<ProductCategory> findByNamaContainsIgnoreCase(String nama, Pageable pageable);

    /** Ini untuk Report */
    public List<ProductCategory> findByNamaContainsIgnoreCase(String nama);

}
