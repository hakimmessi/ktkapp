package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.requests.kioskStatusRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskStatusResponse;
import com.ktk.ktkapp.service.kiosk.kioskStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosk-statuses")
public class kioskStatusController {

    @Autowired
    private kioskStatusService kioskStatusService;

    @PostMapping
    public ResponseEntity<kioskStatusResponse> create(@Valid @RequestBody kioskStatusRequest request) {
        kioskStatusResponse response = kioskStatusService.createKioskStatus(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<kioskStatusResponse>> getAll() {
        return ResponseEntity.ok(kioskStatusService.getAllKioskStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<kioskStatusResponse> getById(@PathVariable Integer id) {
        return kioskStatusService.getKioskStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<kioskStatusResponse> update(@PathVariable Integer id,
                                                      @Valid @RequestBody kioskStatusRequest request) {
        return ResponseEntity.ok(kioskStatusService.updateKioskStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        kioskStatusService.deleteKioskStatus(id);
        return ResponseEntity.noContent().build();
    }
}
