package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.responses.kiosk.kioskClientResponse;
import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.service.kioskClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kiosk-clients")
public class kioskClientController {
    @Autowired
    private kioskClientService service;

    @GetMapping
    public List<kioskClientModel> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<kioskClientModel> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/create")
    public kioskClientResponse create(@RequestBody kioskClientModel client) {
        return service.save(client);
    }


    @PatchMapping("/{clientId}/link-rep/{clientRepId}")
    public kioskClientResponse linkRep(@PathVariable Long clientId, @PathVariable Long clientRepId) {
        return service.linkClientRepToClient(clientId, clientRepId);
    }

    @PutMapping("/{id}")
    public kioskClientResponse update(@PathVariable Long id, @RequestBody kioskClientModel client) {
        client.setClientId(id);
        return service.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
