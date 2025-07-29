package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.technician.responses.altronHubResponse;
import com.ktk.ktkapp.dto.technician.altronHub;
import com.ktk.ktkapp.service.altronHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/altron-hubs")
public class altronController {

    @Autowired
    private altronHubService altronHubService;

    @PostMapping("/create")
    public ResponseEntity<altronHubResponse> createAltronHub(@RequestBody altronHub hubDto) {
        altronHubResponse createdHub = altronHubService.createAltronHub(hubDto);
        return new ResponseEntity<>(createdHub, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<altronHubResponse>> getAllAltronHubs() {
        List<altronHubResponse> hubs = altronHubService.getAllAltronHubs();
        return ResponseEntity.ok(hubs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<altronHubResponse> getAltronHubById(@PathVariable Integer id) {
        return altronHubService.getAltronHubById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<altronHubResponse> updateAltronHub(@PathVariable Integer id, @RequestBody altronHub hubDetails) {
        // The EntityNotFoundException from the service will result in a 404 if the hub doesn't exist.
        altronHubResponse updatedHub = altronHubService.updateAltronHub(id, hubDetails);
        return ResponseEntity.ok(updatedHub);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAltronHub(@PathVariable Integer id) {
        altronHubService.deleteAltronHub(id);
        return ResponseEntity.noContent().build();
    }
}