package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface kioskTypeRepo extends JpaRepository<kioskTypeModel, Integer> {
    // Additional query methods if needed
    Optional<kioskTypeModel> findByKioskTypeId(Integer kioskTypeId);
}
