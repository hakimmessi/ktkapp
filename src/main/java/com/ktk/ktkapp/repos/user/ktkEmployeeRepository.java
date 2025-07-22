package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.role.ktkEmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ktkEmployeeRepository extends JpaRepository<ktkEmployeeModel, Long> {
    Optional<ktkEmployeeModel> findByUser_UserId(Long userId);
}
