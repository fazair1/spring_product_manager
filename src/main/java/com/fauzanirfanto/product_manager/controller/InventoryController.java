package com.fauzanirfanto.product_manager.controller;

import com.fauzanirfanto.product_manager.config.OtherConfig;
import com.fauzanirfanto.product_manager.dto.validation.ValInventoryDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValProductDTO;
import com.fauzanirfanto.product_manager.service.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id, HttpServletRequest request){
        return inventoryService.delete(id,request);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValInventoryDTO valInventoryDTO, HttpServletRequest request) {
        return inventoryService.save(inventoryService.convertToEntity(valInventoryDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody ValInventoryDTO valInventoryDTO, HttpServletRequest request) {
        return inventoryService.update(id, inventoryService.convertToEntity(valInventoryDTO), request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, OtherConfig.getPageDefault(), Sort.by("id"));

        return inventoryService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return inventoryService.findById(id, request);
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
        return inventoryService.findByParam(pageable, column, value, request);
    }

    private String sortColumnByMap(String sortBy){
        switch (sortBy){
            case "product":sortBy = "product";break;
            case "warehouse":sortBy = "warehouse";break;
            case "stock":sortBy = "stock";break;
            default:sortBy = "id";
        }
        return sortBy;
    }

}
