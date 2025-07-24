package com.ktk.ktkapp.service.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskStatus;
import com.ktk.ktkapp.model.kiosk.kioskStatusModel;
import com.ktk.ktkapp.repos.kiosk.kioskStatusRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class kioskStatusService {

    @Autowired
    private kioskStatusRepo kioskStatusRepo;

    public kioskStatus createKioskStatus(kioskStatus kioskStatusDto) {
        kioskStatusModel model = toModel(kioskStatusDto);
        kioskStatusModel savedModel = kioskStatusRepo.save(model);
        return toDto(savedModel);
    }

    public List<kioskStatus> getAllKioskStatuses() {
        return kioskStatusRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<kioskStatus> getKioskStatusById(Integer id) {
        return kioskStatusRepo.findById(id).map(this::toDto);
    }

    public kioskStatus updateKioskStatus(Integer id, kioskStatus kioskStatusDetails) {
        kioskStatusModel existingStatus = kioskStatusRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KioskStatus not found with id: " + id));

        existingStatus.setKioskStatusName(kioskStatusDetails.getKioskStatusName());
        kioskStatusModel updatedStatus = kioskStatusRepo.save(existingStatus);
        return toDto(updatedStatus);
    }

    public void deleteKioskStatus(Integer id) {
        if (!kioskStatusRepo.existsById(id)) {
            throw new EntityNotFoundException("KioskStatus not found with id: " + id);
        }
        kioskStatusRepo.deleteById(id);
    }

    private kioskStatus toDto(kioskStatusModel model) {
        return new kioskStatus(model.getKioskStatusId(), model.getKioskStatusName());
    }

    private kioskStatusModel toModel(kioskStatus dto) {
        return new kioskStatusModel(dto.getKioskStatusId(), dto.getKioskStatusName());
    }
}