package com.fauzanirfanto.product_manager.repositories;

import com.fauzanirfanto.product_manager.model.Inventory;
import com.fauzanirfanto.product_manager.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    /** Select * From MstGroupMenu WHERE toLower(Email) Like toLower('%chihuy%') */
    public Page<Inventory> findByStock(Integer stock, Pageable pageable);

    /** Ini untuk Report */
    public List<Inventory> findByStock(Integer stock);

    @Query(value = "SELECT x FROM Inventory x WHERE lower(x.product.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Inventory> cariProduct(String product, Pageable pageable);

    @Query(value = "SELECT x FROM Inventory x WHERE lower(x.product.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Inventory> cariProduct(String product);

    @Query(value = "SELECT x FROM Inventory x WHERE lower(x.warehouse.nama) LIKE lower(concat('%',?1,'%'))")
    public Page<Inventory> cariWarehouse(String warehouse, Pageable pageable);

    @Query(value = "SELECT x FROM Inventory x WHERE lower(x.warehouse.nama) LIKE lower(concat('%',?1,'%'))")
    public List<Inventory> cariWarehouse(String warehouse);

    public Page<Inventory> findByProductIdAndWarehouseId(Long productId, Long warehouseId, Pageable pageable);

//    public List<Inventory> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

    public Optional<Inventory> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

//    @Query(value = "SELECT x FROM Inventory x WHERE x.product.id AND x.warehouse.id ")
//    public Page<Inventory> cariInventory(Long product, Long warehouse, Pageable pageable);
//
//    @Query(value = "SELECT x FROM Inventory x WHERE lower(x.warehouse.nama) LIKE lower(concat('%',?1,'%'))")
//    public List<Inventory> cariInventory(Long product, Long warehouse);

}
