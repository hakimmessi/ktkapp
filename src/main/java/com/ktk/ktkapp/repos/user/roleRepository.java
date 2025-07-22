package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.role.roleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRepository  extends JpaRepository<roleModel, Long> {
        Optional<roleModel> findByName(String name);
    }
