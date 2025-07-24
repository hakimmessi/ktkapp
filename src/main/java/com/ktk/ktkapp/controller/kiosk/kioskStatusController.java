package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskStatus;
import com.ktk.ktkapp.service.kiosk.kioskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosk-statuses")
public class kioskStatusController {

    @Autowired
    private kioskStatusService kioskStatusService;

    @PostMapping
    public ResponseEntity<kioskStatus> createKioskStatus(@RequestBody kioskStatus kioskStatusDto) {
        kioskStatus createdStatus = kioskStatusService.createKioskStatus(kioskStatusDto);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<kioskStatus>> getAllKioskStatuses() {
        List<kioskStatus> statuses = kioskStatusService.getAllKioskStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<kioskStatus> getKioskStatusById(@PathVariable Integer id) {
        return kioskStatusService.getKioskStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<kioskStatus> updateKioskStatus(@PathVariable Integer id, @RequestBody kioskStatus kioskStatusDetails) {
        kioskStatus updatedStatus = kioskStatusService.updateKioskStatus(id, kioskStatusDetails);
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKioskStatus(@PathVariable Integer id) {
        kioskStatusService.deleteKioskStatus(id);
        return ResponseEntity.noContent().build();
    }
}