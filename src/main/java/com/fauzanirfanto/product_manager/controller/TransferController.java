package com.fauzanirfanto.product_manager.controller;

import com.fauzanirfanto.product_manager.config.OtherConfig;
import com.fauzanirfanto.product_manager.dto.validation.ValInventoryDTO;
import com.fauzanirfanto.product_manager.dto.validation.ValTransferDTO;
import com.fauzanirfanto.product_manager.service.TransferService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(value = "id") Long id, HttpServletRequest request){
        return transferService.delete(id,request);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValTransferDTO valTransferDTO, HttpServletRequest request) {
        return transferService.save(transferService.convertToEntity(valTransferDTO), request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody ValTransferDTO valTransferDTO, HttpServletRequest request) {
        return transferService.update(id, transferService.convertToEntity(valTransferDTO), request);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(HttpServletRequest request) {
        Pageable pageable = PageRequest.of(0, OtherConfig.getPageDefault(), Sort.by("id"));

        return transferService.findAll(pageable, request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        return transferService.findById(id, request);
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
        return transferService.findByParam(pageable, column, value, request);
    }

    private String sortColumnByMap(String sortBy){
        switch (sortBy){
            case "product":sortBy = "product";break;
            case "warehouseFrom":sortBy = "warehouseFrom";break;
            case "warehouseTo":sortBy = "warehouseTo";break;
            case "stock":sortBy = "stock";break;
            default:sortBy = "id";
        }
        return sortBy;
    }

}
