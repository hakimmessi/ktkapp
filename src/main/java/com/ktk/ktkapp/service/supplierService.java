package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.supplier.supplier;
import com.ktk.ktkapp.model.supplier.supplierModel;
import com.ktk.ktkapp.repos.supplier.supplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class supplierService {

    @Autowired
    private supplierRepo supplierRepo;

    public supplier createSupplier(supplier supplierDto) {
        supplierModel model = new supplierModel(
            null,
            supplierDto.getName(),
            supplierDto.getContactName(),
            supplierDto.getContactPhone(),
            supplierDto.getCountry(),
            supplierDto.getCreatedAt()
        );
        supplierModel saved = supplierRepo.save(model);
        return toDto(saved);
    }

    public List<supplier> getAllSuppliers() {
        return supplierRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private supplier toDto(supplierModel model) {
        return new supplier(
            model.getSupplierId(),
            model.getName(),
            model.getContactName(),
            model.getContactPhone(),
            model.getCountry(),
            model.getCreatedAt()
        );
    }
}
