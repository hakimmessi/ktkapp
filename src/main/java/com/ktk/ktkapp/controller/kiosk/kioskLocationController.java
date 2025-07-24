package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskLocation;
import com.ktk.ktkapp.dto.responses.kiosk.kioskLocationResponse;
import com.ktk.ktkapp.service.kiosk.kioskLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosk-locations")
public class kioskLocationController {

    @Autowired
    private kioskLocationService locationService;

    @PostMapping("/add")
    public ResponseEntity<kioskLocationResponse> createKioskLocation(@RequestBody kioskLocation locationDto) {
        kioskLocationResponse createdLocation = locationService.createLocation(locationDto);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<kioskLocationResponse>> getAllKioskLocations() {
        List<kioskLocationResponse> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<kioskLocationResponse> getKioskLocationById(@PathVariable Integer id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<kioskLocationResponse> updateKioskLocation(@PathVariable Integer id) {
        kioskLocation locationDetails = new kioskLocation(); // This should be populated with actual data}
        kioskLocationResponse updatedLocation = locationService.updateLocation(id, locationDetails);
        return ResponseEntity.ok(updatedLocation);
    }
}