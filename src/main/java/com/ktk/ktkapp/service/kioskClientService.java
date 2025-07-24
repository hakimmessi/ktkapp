package com.ktk.ktkapp.service;

import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.model.role.kioskClientRepModel;
import com.ktk.ktkapp.repos.kiosk.kioskClientRepo;
import com.ktk.ktkapp.repos.user.kioskClientRepRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktk.ktkapp.dto.responses.kiosk.kioskClientResponse;

import java.util.List;
import java.util.Optional;

@Service
public class kioskClientService {
    @Autowired
    private kioskClientRepo repo;

    @Autowired
    private kioskClientRepRepository clientRepRepo;

    public List<kioskClientModel> findAll() {
        return repo.findAll();
    }

    public Optional<kioskClientModel> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public kioskClientResponse linkClientRepToClient(Long clientId, Long clientRepId) {
        // Find the client (organization)
        kioskClientModel client = repo.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Kiosk Client not found with ID: " + clientId));

        // Find the client representative (person)
        kioskClientRepModel clientRep = clientRepRepo.findById(clientRepId)
                .orElseThrow(() -> new IllegalArgumentException("Kiosk Client Representative not found with ID: " + clientRepId));

        // Set the relationship
        client.setClientRep(clientRep);

        // Save the updated client. JPA will now commit this change.
        kioskClientModel savedClient = repo.save(client);

        return mapToResponseDTO(savedClient);
    }


    public kioskClientResponse save(kioskClientModel client) {
        kioskClientModel savedClient = repo.save(client);
        return mapToResponseDTO(savedClient);
    }

    private kioskClientResponse mapToResponseDTO(kioskClientModel client) {
        kioskClientResponse data_object = new kioskClientResponse();
        data_object.setClientId(client.getClientId());
        data_object.setClientName(client.getClientName());
        data_object.setAddress(client.getAddress());
        data_object.setCity(client.getCity());
        data_object.setProvince(client.getProvince());
        data_object.setContractStartDate(client.getContractStartDate());
        data_object.setContractEndDate(client.getContractEndDate());
        data_object.setIsActive(client.getIsActive());
        data_object.setCreatedAt(client.getCreatedAt());
        data_object.setUpdatedAt(client.getUpdatedAt());

        // Handle the nested object safely
        if (client.getClientRep() != null) {
            data_object.setClientRepId(client.getClientRep().getClientRepId());
            data_object.setClientRepJobTitle(client.getClientRep().getJobTitle());
        }

        return data_object;
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
