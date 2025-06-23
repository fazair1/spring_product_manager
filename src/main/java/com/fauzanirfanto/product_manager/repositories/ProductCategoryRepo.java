package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
}
