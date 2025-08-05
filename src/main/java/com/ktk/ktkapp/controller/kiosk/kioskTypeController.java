package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.requests.kioskTypeRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskTypeResponse;
import com.ktk.ktkapp.service.kiosk.kioskTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosk-types")
public class kioskTypeController {

    @Autowired
    private kioskTypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<kioskTypeResponse> createKioskType(@Valid @RequestBody kioskTypeRequest request) {
        kioskTypeResponse response = typeService.createKioskType(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<kioskTypeResponse>> getAllKioskTypes() {
        List<kioskTypeResponse> types = typeService.getAllKioskTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<kioskTypeResponse> getKioskTypeById(@PathVariable Integer id) {
        return typeService.getKioskTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<kioskTypeResponse> updateKioskType(@PathVariable Integer id, @RequestBody kioskTypeRequest request) {
        kioskTypeResponse updatedType = typeService.updateKioskType(id, request);
        return ResponseEntity.ok(updatedType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKioskType(@PathVariable Integer id) {
        typeService.deleteKioskType(id);
        return ResponseEntity.noContent().build();
    }
}
