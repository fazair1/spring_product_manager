package com.fauzanirfanto.product_manager.controller;

import com.fauzanirfanto.product_manager.config.OtherConfig;
import com.fauzanirfanto.product_manager.dto.validation.ValProductDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValWarehouseDTO;
import com.fauzanirfanto.product_manager.service.ProductService;
import com.fauzanirfanto.product_manager.service.WarehouseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id, HttpServletRequest request){
        return warehouseService.delete(id,request);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValWarehouseDTO valWarehouseDTO, HttpServletRequest request) {
        return warehouseService.save(warehouseService.convertToEntity(valWarehouseDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody ValWarehouseDTO valWarehouseDTO, HttpServletRequest request) {
        return warehouseService.update(id, warehouseService.convertToEntity(valWarehouseDTO), request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, OtherConfig.getPageDefault(), Sort.by("id"));

        return warehouseService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return warehouseService.findById(id, request);
    }

    @GetMapping("/{sort}/{sortBy}/{page}")
    public ResponseEntity<Object> findByParam(
            @PathVariable(value = "sort") String sort,
            @PathVariable(value = "sortBy") String sortBy,
            @PathVariable(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "column") String column,
            @RequestParam(value = "value") String value,
            HttpServletRequest request){

        Pageable pageable = null;
        sortBy = sortColumnByMap(sortBy);
        switch (sort) {
            case "asc":pageable = PageRequest.of(page, size, Sort.by(sortBy));break;
            default: pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        }
        return warehouseService.findByParam(pageable, column, value, request);
    }

    private String sortColumnByMap(String sortBy){
        switch (sortBy){
            case "nama":sortBy = "nama";break;
            case "alamat":sortBy = "alamat";break;
            default:sortBy = "id";
        }
        return sortBy;
    }

}
