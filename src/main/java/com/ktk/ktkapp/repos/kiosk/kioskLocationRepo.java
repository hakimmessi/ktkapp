package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface kioskLocationRepo extends JpaRepository<kioskLocationModel, Integer> {
    Optional<kioskLocationModel> findByLocationName(String name);
}