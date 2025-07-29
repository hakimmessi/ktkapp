package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.component.component;
import com.ktk.ktkapp.dto.component.responses.componentResponse;
import com.ktk.ktkapp.model.component.componentModel;
import com.ktk.ktkapp.model.kiosk.kioskComponentModel;
import com.ktk.ktkapp.repos.component.componentRepo;
import com.ktk.ktkapp.repos.kiosk.kioskComponentRepo;
import com.ktk.ktkapp.repos.kiosk.kioskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class componentService {

    @Autowired
    private componentRepo componentRepo;

    @Autowired
    private kioskComponentRepo kioskComponentRepo;

    @Autowired
    private kioskRepo kioskRepo;

    public componentResponse createComponent(component componentDto) {
        componentModel model = toModel(componentDto);
        componentModel saved = componentRepo.save(model);
        return toResponseDto(saved);
    }

    public List<componentResponse> getAllComponents() {
        return componentRepo.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<componentResponse> getComponentById(Integer id) {
        return componentRepo.findById(id).map(this::toResponseDto);
    }

    public componentResponse updateComponent(Integer id, component componentDetails) {
        componentModel existingComponent = componentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Component not found with id: " + id));

        existingComponent.setName(componentDetails.getName());
        existingComponent.setSupplierId(componentDetails.getSupplierId());
        existingComponent.setSerialNumber(componentDetails.getSerialNumber());
        existingComponent.setModel(componentDetails.getModel());
        existingComponent.setCost(componentDetails.getCost());

        componentModel updatedComponent = componentRepo.save(existingComponent);
        return toResponseDto(updatedComponent);
    }

    public void deleteComponent(Integer id) {
        if (!componentRepo.existsById(id)) {
            throw new EntityNotFoundException("Component not found with id: " + id);
        }
        componentRepo.deleteById(id);
    }

    /**
     * Assigns a component to a kiosk, creating an entry in the kiosk_component table.
     * It first validates that both the kiosk and component exist.
     */
    public void assignComponentToKiosk(Integer kioskId, Integer componentId) {
        // Check if the kiosk exists
        if (!kioskRepo.existsById(kioskId)) {
            throw new EntityNotFoundException("Cannot assign component: Kiosk not found with id: " + kioskId);
        }

        // Check if the component exists
        if (!componentRepo.existsById(componentId)) {
            throw new EntityNotFoundException("Cannot assign component: Component not found with id: " + componentId);
        }

        // Optional: Check if the association already exists to provide a clearer error
        kioskComponentModel.KioskComponentId associationId = new kioskComponentModel.KioskComponentId(kioskId, componentId);
        if (kioskComponentRepo.existsById(associationId)) {
            throw new IllegalStateException("Component with id " + componentId + " is already assigned to kiosk with id " + kioskId);
        }

        // All checks passed, create and save the association
        kioskComponentModel association = new kioskComponentModel();
        association.setKioskId(kioskId);
        association.setComponentId(componentId);
        kioskComponentRepo.save(association);
    }

    private componentResponse toResponseDto(componentModel model) {
        return new componentResponse(
                model.getComponentId(),
                model.getName(),
                model.getSupplierId(),
                model.getSerialNumber(),
                model.getModel(),
                model.getCost(),
                model.getCreatedAt()
        );
    }

    private componentModel toModel(component dto) {
        componentModel model = new componentModel();
        model.setName(dto.getName());
        model.setSupplierId(dto.getSupplierId());
        model.setSerialNumber(dto.getSerialNumber());
        model.setModel(dto.getModel());
        model.setCost(dto.getCost());

        return model;
    }
}