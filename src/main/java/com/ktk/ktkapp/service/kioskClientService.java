package com.ktk.ktkapp.service;

import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.repos.kiosk.kioskClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class kioskClientService {
    @Autowired
    private kioskClientRepo repo;

    public List<kioskClientModel> findAll() {
        return repo.findAll();
    }

    public Optional<kioskClientModel> findById(Long id) {
        return repo.findById(id);
    }

    public kioskClientModel save(kioskClientModel client) {
        if (client.getClientRep() == null) {
            throw new IllegalArgumentException("A client representative must be assigned to the organization.");
        }
        return repo.save(client);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
