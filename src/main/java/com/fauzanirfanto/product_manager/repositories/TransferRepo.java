package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.Inventory;
import com.fauzanirfanto.product_manager.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer, Long> {

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.inventory.product.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Transfer> cariProductTransfer(String product, Pageable pageable);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.inventory.product.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Transfer> cariProductTransfer(String product);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.warehouseFrom.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Transfer> cariWarehouseFromTransfer(String warehouse, Pageable pageable);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.warehouseFrom.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Transfer> cariWarehouseFromTransfer(String warehouse);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.warehouseTo.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Transfer> cariWarehouseToTransfer(String warehouse, Pageable pageable);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.warehouseTo.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Transfer> cariWarehouseToTransfer(String warehouse);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.status.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Transfer> cariStatusTransfer(String status, Pageable pageable);

    @Query(value = "SELECT x FROM Transfer x WHERE lower(x.status.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Transfer> cariStatusTransfer(String status);

    public Page<Transfer> findByStock(Integer stock, Pageable pageable);

    public List<Transfer> findByStock(Integer stock);

}
