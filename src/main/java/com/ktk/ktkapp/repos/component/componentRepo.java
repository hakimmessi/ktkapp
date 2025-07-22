package com.ktk.ktkapp.repos.component;

import com.ktk.ktkapp.model.component.componentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface componentRepo extends JpaRepository<componentModel, Integer> {
    // find by component name
    Optional<componentModel>findByName(String name);
}
