package com.ktk.ktkapp.service.kiosk;

import com.ktk.ktkapp.dto.kiosk.mapper.kioskStatusMapper;
import com.ktk.ktkapp.dto.kiosk.requests.kioskStatusRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskStatusResponse;
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

    public kioskStatusResponse createKioskStatus(kioskStatusRequest request) {
        kioskStatusModel model = kioskStatusMapper.toModel(request);
        kioskStatusModel saved = kioskStatusRepo.save(model);
        return kioskStatusMapper.toResponse(saved);
    }

    public List<kioskStatusResponse> getAllKioskStatuses() {
        return kioskStatusRepo.findAll().stream()
                .map(kioskStatusMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<kioskStatusResponse> getKioskStatusById(Integer id) {
        return kioskStatusRepo.findById(id)
                .map(kioskStatusMapper::toResponse);
    }

    public kioskStatusResponse updateKioskStatus(Integer id, kioskStatusRequest request) {
        kioskStatusModel model = kioskStatusRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("KioskStatus not found with id: " + id));

        model.setKioskStatusName(request.getKioskStatusName());
        kioskStatusModel updated = kioskStatusRepo.save(model);
        return kioskStatusMapper.toResponse(updated);
    }

    public void deleteKioskStatus(Integer id) {
        if (!kioskStatusRepo.existsById(id)) {
            throw new EntityNotFoundException("KioskStatus not found with id: " + id);
        }
        kioskStatusRepo.deleteById(id);
    }
}