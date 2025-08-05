package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface kioskStatusRepo extends JpaRepository<kioskStatusModel, Integer> {
    boolean existsByKioskStatusName(String name);
}
