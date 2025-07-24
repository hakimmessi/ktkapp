package com.ktk.ktkapp.service.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskLocation;
import com.ktk.ktkapp.dto.responses.kiosk.kioskLocationResponse;
import com.ktk.ktkapp.model.kiosk.kioskLocationModel;
import com.ktk.ktkapp.repos.kiosk.kioskLocationRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class kioskLocationService {

    @Autowired
    private kioskLocationRepo locationRepository;

    public kioskLocationResponse createLocation(kioskLocation locationDto) {
        kioskLocationModel model = toModel(locationDto);
        kioskLocationModel savedModel = locationRepository.save(model);
        return toResponseDto(savedModel);
    }

    public List<kioskLocationResponse> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<kioskLocationResponse> getLocationById(Integer id) {
        return locationRepository.findById(id).map(this::toResponseDto);
    }

    public kioskLocationResponse updateLocation(Integer id, kioskLocation locationDetails) {
        kioskLocationModel existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id: " + id));

        existingLocation.setLocationName(locationDetails.getLocationName());
        existingLocation.setPhysicalAddress(locationDetails.getPhysicalAddress());
        existingLocation.setGpsCoordinates(locationDetails.getGpsCoordinates());
        existingLocation.setLocationType(locationDetails.getLocationType());

        kioskLocationModel updatedLocation = locationRepository.save(existingLocation);
        return toResponseDto(updatedLocation);
    }

    public void deleteLocation(Integer id) {
        if (!locationRepository.existsById(id)) {
            throw new EntityNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }

    private kioskLocationResponse toResponseDto(kioskLocationModel model) {
        return new kioskLocationResponse(
                model.getLocationId(),
                model.getLocationName(),
                model.getPhysicalAddress(),
                model.getGpsCoordinates(),
                model.getLocationType(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        );
    }

    private kioskLocationModel toModel(kioskLocation dto) {
        return new kioskLocationModel(
                dto.getLocationId(),
                dto.getLocationName(),
                dto.getPhysicalAddress(),
                dto.getGpsCoordinates(),
                dto.getLocationType(),
                null,
                null
        );
    }
}