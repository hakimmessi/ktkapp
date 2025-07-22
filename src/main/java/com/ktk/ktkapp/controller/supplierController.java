package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.supplier.supplier;
import com.ktk.ktkapp.service.supplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class supplierController {

    @Autowired
    private supplierService supplierService;

    @PostMapping
    public supplier createSupplier(@RequestBody supplier supplierDto) {
        return supplierService.createSupplier(supplierDto);
    }

    @GetMapping
    public List<supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }
}
