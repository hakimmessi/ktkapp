package com.ktk.ktkapp.service.kiosk;

import com.ktk.ktkapp.dto.kiosk.kiosk;
import com.ktk.ktkapp.dto.kiosk.responses.kioskResponse;
import com.ktk.ktkapp.model.kiosk.kioskModel;
import com.ktk.ktkapp.repos.kiosk.kioskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class kioskService {

    @Autowired
    private kioskRepo kioskRepo;

    public kioskResponse createKiosk(kiosk kioskDto) {
        kioskModel model = toModel(kioskDto);
        kioskModel savedModel = kioskRepo.save(model);
        return toResponseDto(savedModel);
    }

    public List<kioskResponse> getAllKiosks() {
        return kioskRepo.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<kioskResponse> getKioskById(Integer id) {
        return kioskRepo.findById(id).map(this::toResponseDto);
    }

    public kioskResponse updateKiosk(Integer id, kiosk kioskDetails) {
        kioskModel existingKiosk = kioskRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kiosk not found with id: " + id));

        // Update fields from DTO
        existingKiosk.setTerminalId(kioskDetails.getTerminalId());
        existingKiosk.setAssetNumber(kioskDetails.getAssetNumber());
        existingKiosk.setDateOfInstallation(kioskDetails.getDateOfInstallation());
        existingKiosk.setLastMaintenance(kioskDetails.getLastMaintenance());
        existingKiosk.setKioskTypeId(kioskDetails.getKioskTypeId());
        existingKiosk.setKioskStatusId(kioskDetails.getKioskStatusId());
        existingKiosk.setAltronHubId(kioskDetails.getAltronHubId());
        existingKiosk.setClientId(kioskDetails.getClientId());
        existingKiosk.setCustodianId(kioskDetails.getCustodianId());
        existingKiosk.setLocationId(kioskDetails.getLocationId());

        kioskModel updatedKiosk = kioskRepo.save(existingKiosk);
        return toResponseDto(updatedKiosk);
    }

    public void deleteKiosk(Integer id) {
        if (!kioskRepo.existsById(id)) {
            throw new EntityNotFoundException("Kiosk not found with id: " + id);
        }
        kioskRepo.deleteById(id);
    }

    private kioskResponse toResponseDto(kioskModel model) {
        return new kioskResponse(
                model.getKioskId(),
                model.getTerminalId(),
                model.getAssetNumber(),
                model.getDateOfInstallation(),
                model.getLastMaintenance(),
                model.getKioskTypeId(),
                model.getKioskStatusId(),
                model.getAltronHubId(),
                model.getClientId(),
                model.getCustodianId(),
                model.getLocationId(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }

    // Helper to map from Request DTO to Entity
    private kioskModel toModel(kiosk dto) {
        kioskModel model = new kioskModel();
        model.setTerminalId(dto.getTerminalId());
        model.setAssetNumber(dto.getAssetNumber());
        model.setDateOfInstallation(dto.getDateOfInstallation());
        model.setLastMaintenance(dto.getLastMaintenance());
        model.setKioskTypeId(dto.getKioskTypeId());
        model.setKioskStatusId(dto.getKioskStatusId());
        model.setAltronHubId(dto.getAltronHubId());
        model.setClientId(dto.getClientId());
        model.setCustodianId(dto.getCustodianId());
        model.setLocationId(dto.getLocationId());

        return model;
    }
}