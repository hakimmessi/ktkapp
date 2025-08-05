package com.ktk.ktkapp.service.kiosk;


import com.ktk.ktkapp.dto.kiosk.mapper.kioskTypeMapper;
import com.ktk.ktkapp.dto.kiosk.requests.kioskTypeRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskTypeResponse;
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

    public kioskTypeResponse createKioskType(kioskTypeRequest request) {
        kioskTypeModel model = kioskTypeMapper.toModel(request);
        kioskTypeModel savedModel = kioskTypeRepo.save(model);
        return kioskTypeMapper.toResponse(savedModel);
    }

    public List<kioskTypeResponse> getAllKioskTypes() {
        return kioskTypeRepo.findAll().stream()
                .map(kioskTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<kioskTypeResponse> getKioskTypeById(Integer id) {
        return kioskTypeRepo.findById(id).map(kioskTypeMapper::toResponse);
    }

    public kioskTypeResponse updateKioskType(Integer id, kioskTypeRequest request) {
        kioskTypeModel existingType = kioskTypeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KioskType not found with id: " + id));

        existingType.setKioskTypeName(request.getKioskTypeName());
        kioskTypeModel updatedType = kioskTypeRepo.save(existingType);
        return kioskTypeMapper.toResponse(updatedType);
    }

    public void deleteKioskType(Integer id) {
        if (!kioskTypeRepo.existsById(id)) {
            throw new EntityNotFoundException("KioskType not found with id: " + id);
        }
        kioskTypeRepo.deleteById(id);
    }
}