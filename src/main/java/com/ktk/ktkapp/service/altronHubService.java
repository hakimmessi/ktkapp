package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.technician.altronHub;
import com.ktk.ktkapp.model.technician.altronHubModel;
import com.ktk.ktkapp.repos.altron.altronHubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class altronHubService {

    @Autowired
    private altronHubRepo altronHubRepo;

    public altronHub createAltronHub(altronHub hubDto) {
        altronHubModel model = new altronHubModel(
            null,
            hubDto.getHubName(),
            hubDto.getAddress(),
            hubDto.getCity(),
            hubDto.getProvince(),
            hubDto.getGpsCoordinates(),
            hubDto.getContactName(),
            hubDto.getContactEmail(),
            hubDto.getContactPhone(),
            hubDto.getCreatedAt(),
            hubDto.getUpdatedAt()
        );
        altronHubModel saved = altronHubRepo.save(model);
        return toDto(saved);
    }

    public List<altronHub> getAllAltronHubs() {
        return altronHubRepo.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private altronHub toDto(altronHubModel model) {
        return new altronHub(
            model.getHubId(),
            model.getHubName(),
            model.getAddress(),
            model.getCity(),
            model.getProvince(),
            model.getGpsCoordinates(),
            model.getContactName(),
            model.getContactEmail(),
            model.getContactPhone(),
            model.getCreatedAt(),
            model.getUpdatedAt()
        );
    }
}
