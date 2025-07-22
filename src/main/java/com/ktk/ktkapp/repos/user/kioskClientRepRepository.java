package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.role.kioskClientRepModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface kioskClientRepRepository extends JpaRepository<kioskClientRepModel, Long> {
    Optional<kioskClientRepModel> findByClientRepId(Long clientRepId);
}
