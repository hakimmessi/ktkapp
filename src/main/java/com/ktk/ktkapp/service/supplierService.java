package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.supplier.responses.supplierResponse;
import com.ktk.ktkapp.dto.supplier.supplier;
import com.ktk.ktkapp.model.supplier.supplierModel;
import com.ktk.ktkapp.repos.supplier.supplierRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class supplierService {

    @Autowired
    private supplierRepo supplierRepo;

    public supplierResponse createSupplier(supplier supplierDto) {
        supplierModel model = toModel(supplierDto);
        supplierModel saved = supplierRepo.save(model);
        return toResponseDto(saved);
    }

    public List<supplierResponse> getAllSuppliers() {
        return supplierRepo.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<supplierResponse> getSupplierById(Integer id) {
        return supplierRepo.findById(id).map(this::toResponseDto);
    }

    public supplierResponse updateSupplier(Integer id, supplier supplierDetails) {
        supplierModel existingSupplier = supplierRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));

        existingSupplier.setName(supplierDetails.getName());
        existingSupplier.setContactName(supplierDetails.getContactName());
        existingSupplier.setContactPhone(supplierDetails.getContactPhone());
        existingSupplier.setCountry(supplierDetails.getCountry());

        supplierModel updatedSupplier = supplierRepo.save(existingSupplier);
        return toResponseDto(updatedSupplier);
    }

    public void deleteSupplier(Integer id) {
        if (!supplierRepo.existsById(id)) {
            throw new EntityNotFoundException("Supplier not found with id: " + id);
        }
        supplierRepo.deleteById(id);
    }

    private supplierResponse toResponseDto(supplierModel model) {
        return new supplierResponse(
                model.getSupplierId(),
                model.getName(),
                model.getContactName(),
                model.getContactPhone(),
                model.getCountry(),
                model.getCreatedAt()
        );
    }

    private supplierModel toModel(supplier dto) {
        supplierModel model = new supplierModel();
        model.setName(dto.getName());
        model.setContactName(dto.getContactName());
        model.setContactPhone(dto.getContactPhone());
        model.setCountry(dto.getCountry());
        // ID and createdAt are handled by JPA/DB
        return model;
    }
}