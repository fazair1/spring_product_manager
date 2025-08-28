package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.Status;
import com.fauzanirfanto.product_manager.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {
}
