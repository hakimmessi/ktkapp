package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.technician.altronHub;
import com.ktk.ktkapp.service.altronHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/altron-hubs")
public class altronController {

    @Autowired
    private altronHubService altronHubService;

    @PostMapping
    public altronHub createAltronHub(@RequestBody altronHub hubDto) {
        return altronHubService.createAltronHub(hubDto);
    }

    @GetMapping
    public List<altronHub> getAllAltronHubs() {
        return altronHubService.getAllAltronHubs();
    }
}
