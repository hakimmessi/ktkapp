package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.role.ktkUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ktkUserRepository extends JpaRepository<ktkUserModel, Long> {
    Optional<ktkUserModel> findByUser_UserId(Long userId);
}
