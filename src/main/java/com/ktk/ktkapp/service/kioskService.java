package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.kiosk.kiosk;
import com.ktk.ktkapp.model.kiosk.kioskModel;
import com.ktk.ktkapp.repos.kiosk.kioskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class kioskService {

    @Autowired
    private kioskRepo kioskRepo;

    public kiosk createKiosk(kiosk kioskDto) {
        kioskModel model = new kioskModel(
            null,
            kioskDto.getTerminalId(),
            kioskDto.getAssetNumber(),
            kioskDto.getAddress(),
            kioskDto.getDateOfInstallation(),
            kioskDto.getLastMaintenance(),
            kioskDto.getKioskTypeId(),
            kioskDto.getKioskStatusId(),
            kioskDto.getAltronHubId(),
            kioskDto.getClientId(),
            kioskDto.getCustodianId(),
            kioskDto.getLocationId(),
            kioskDto.getCreatedAt(),
            kioskDto.getUpdatedAt()
        );
        kioskModel saved = kioskRepo.save(model);
        return toDto(saved);
    }

    public List<kiosk> getAllKiosks() {
        return kioskRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private kiosk toDto(kioskModel model) {
        return new kiosk(
            model.getKioskId(),
            model.getTerminalId(),
            model.getAssetNumber(),
            model.getAddress(),
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
}
