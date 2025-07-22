package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskComponentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface kioskComponentRepo extends JpaRepository<kioskComponentModel, kioskComponentModel.KioskComponentId> {
    Optional<kioskComponentModel>findByKioskIdAndComponentId(Long kioskId, Long componentId);
}
