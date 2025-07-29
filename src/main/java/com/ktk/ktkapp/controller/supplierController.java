package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.supplier.responses.supplierResponse;
import com.ktk.ktkapp.dto.supplier.supplier;
import com.ktk.ktkapp.service.supplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class supplierController {

    @Autowired
    private supplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<supplierResponse> createSupplier(@RequestBody supplier supplierDto) {
        supplierResponse createdSupplier = supplierService.createSupplier(supplierDto);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<supplierResponse>> getAllSuppliers() {
        List<supplierResponse> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<supplierResponse> getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<supplierResponse> updateSupplier(@PathVariable Integer id, @RequestBody supplier supplierDetails) {
        supplierResponse updatedSupplier = supplierService.updateSupplier(id, supplierDetails);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}