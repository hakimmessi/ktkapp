package com.ktk.ktkapp.repos.altron;

import com.ktk.ktkapp.model.technician.altronHubModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface altronHubRepo extends JpaRepository<altronHubModel, Integer> {
    Optional<altronHubModel>findByHubId(Integer hubId);
}
