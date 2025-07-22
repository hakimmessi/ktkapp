package com.ktk.ktkapp.repos.user;

import com.ktk.ktkapp.model.user.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository is an interface that provides access to data in a database
 */
@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    Optional<userModel> findByEmail(String email);
}
