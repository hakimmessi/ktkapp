package com.ktk.ktkapp.service.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskType;
import com.ktk.ktkapp.model.kiosk.kioskTypeModel;
import com.ktk.ktkapp.repos.kiosk.kioskTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class kioskTypeService {

    @Autowired
    private kioskTypeRepo kioskTypeRepo;

    public kioskType createKioskType(kioskType kioskTypeDto) {
        kioskTypeModel model = toModel(kioskTypeDto);
        kioskTypeModel savedModel = kioskTypeRepo.save(model);
        return toDto(savedModel);
    }

    public List<kioskType> getAllKioskTypes() {
        return kioskTypeRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<kioskType> getKioskTypeById(Integer id) {
        return kioskTypeRepo.findById(id).map(this::toDto);
    }

    public kioskType updateKioskType(Integer id, kioskType kioskTypeDetails) {
        kioskTypeModel existingType = kioskTypeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KioskType not found with id: " + id));

        existingType.setKioskTypeName(kioskTypeDetails.getKioskTypeName());
        kioskTypeModel updatedType = kioskTypeRepo.save(existingType);
        return toDto(updatedType);
    }

    public void deleteKioskType(Integer id) {
        if (!kioskTypeRepo.existsById(id)) {
            throw new EntityNotFoundException("KioskType not found with id: " + id);
        }
        kioskTypeRepo.deleteById(id);
    }

    private kioskType toDto(kioskTypeModel model) {
        return new kioskType(model.getKioskTypeId(), model.getKioskTypeName());
    }

    private kioskTypeModel toModel(kioskType dto) {
        return new kioskTypeModel(dto.getKioskTypeId(), dto.getKioskTypeName());
    }
}