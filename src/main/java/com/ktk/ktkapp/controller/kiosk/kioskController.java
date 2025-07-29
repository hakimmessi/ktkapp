package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.kiosk;
import com.ktk.ktkapp.dto.kiosk.responses.kioskResponse;
import com.ktk.ktkapp.service.kiosk.kioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosks")
public class kioskController {

    @Autowired
    private kioskService kioskService;

    @PostMapping("/create")
    public ResponseEntity<kioskResponse> createKiosk(@RequestBody kiosk kioskDto) {
        kioskResponse createdKiosk = kioskService.createKiosk(kioskDto);
        return new ResponseEntity<>(createdKiosk, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<kioskResponse>> getAllKiosks() {
        List<kioskResponse> kiosks = kioskService.getAllKiosks();
        return ResponseEntity.ok(kiosks);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<kioskResponse> getKioskById(@PathVariable Integer id) {
        return kioskService.getKioskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<kioskResponse> updateKiosk(@PathVariable Integer id, @RequestBody kiosk kioskDetails) {
        kioskResponse updatedKiosk = kioskService.updateKiosk(id, kioskDetails);
        return ResponseEntity.ok(updatedKiosk);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteKiosk(@PathVariable Integer id) {
        kioskService.deleteKiosk(id);
        return ResponseEntity.noContent().build();
    }
}