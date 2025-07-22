package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.kiosk.kiosk;
import com.ktk.ktkapp.service.kioskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kiosks")
public class kioskController {

    @Autowired
    private kioskService kioskService;

    @PostMapping
    public kiosk createKiosk(@RequestBody kiosk kioskDto) {

        return kioskService.createKiosk(kioskDto);
    }

    @GetMapping
    public List<kiosk> getAllKiosks() {

        return kioskService.getAllKiosks();
    }
}
