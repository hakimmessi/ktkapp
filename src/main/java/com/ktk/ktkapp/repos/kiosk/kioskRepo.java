package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface kioskRepo extends JpaRepository<kioskModel, Integer> {
    Optional<kioskModel>findByKioskId(Integer kioskId);
}
