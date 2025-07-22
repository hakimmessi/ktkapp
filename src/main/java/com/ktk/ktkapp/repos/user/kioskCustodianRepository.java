package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.role.kioskCustodianModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface kioskCustodianRepository extends JpaRepository<kioskCustodianModel, Long> {
    Optional<kioskCustodianModel> findByUser_UserId(Long userId);
}
