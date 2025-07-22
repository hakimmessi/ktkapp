package com.ktk.ktkapp.repos.supplier;

import com.ktk.ktkapp.model.supplier.supplierModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface supplierRepo extends JpaRepository<supplierModel, Integer> {
    // Additional query methods if needed
    Optional<supplierModel>findByName(String name);
}
