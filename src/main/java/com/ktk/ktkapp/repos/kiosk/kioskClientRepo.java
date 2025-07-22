package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface kioskClientRepo extends JpaRepository<kioskClientModel, Long> {
    Optional<kioskClientModel>findByClientId(Long clientId);
}
