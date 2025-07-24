package com.ktk.ktkapp.controller.kiosk;

import com.ktk.ktkapp.dto.kiosk.kioskType;
import com.ktk.ktkapp.service.kiosk.kioskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosk-types")
public class kioskTypeController {

    @Autowired
    private kioskTypeService kioskTypeService;

    @PostMapping("/create")
    public ResponseEntity<kioskType> createKioskType(@RequestBody kioskType kioskTypeDto) {
        kioskType createdType = kioskTypeService.createKioskType(kioskTypeDto);
        return new ResponseEntity<>(createdType, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<kioskType>> getAllKioskTypes() {
        List<kioskType> types = kioskTypeService.getAllKioskTypes();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<kioskType> getKioskTypeById(@PathVariable Integer id) {
        return kioskTypeService.getKioskTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<kioskType> updateKioskType(@PathVariable Integer id, @RequestBody kioskType kioskTypeDetails) {
        kioskType updatedType = kioskTypeService.updateKioskType(id, kioskTypeDetails);
        return ResponseEntity.ok(updatedType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKioskType(@PathVariable Integer id) {
        kioskTypeService.deleteKioskType(id);
        return ResponseEntity.noContent().build();
    }
}
