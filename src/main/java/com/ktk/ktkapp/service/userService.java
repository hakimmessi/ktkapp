package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.user.*;
import com.ktk.ktkapp.dto.responses.users.userLoginResponse;
import com.ktk.ktkapp.dto.responses.users.userResponse;
import com.ktk.ktkapp.model.role.roleModel;
import com.ktk.ktkapp.model.user.userModel;
import com.ktk.ktkapp.repos.user.roleRepository;
import com.ktk.ktkapp.repos.user.userRepository;
import com.ktk.ktkapp.utilities.passwordUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class userService {
    private final userRepository userRepo;
    private final roleService roleServ;
    private final roleRepository roleRepo;

    public userService(userRepository userRepo, roleService roleServ, roleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleServ = roleServ;
        this.roleRepo = roleRepo;
    }

    /**
     * Create a new user with roles and profile data
     */
    @Transactional
    public userResponse createUser(userCreate createDTO) {
        // Check if email already exists
        if (userRepo.findByEmail(createDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create user entity
        userModel newUser = new userModel();
        newUser.setEmail(createDTO.getEmail().trim());
        newUser.setPasswordHash(passwordUtil.hash(createDTO.getPassword()));
        newUser.setFirstName(createDTO.getFirstName());
        newUser.setSurname(createDTO.getSurname());
        newUser.setPhone(createDTO.getPhone());

        // Calculate passwordExpiryDate: 3 months from current date
        LocalDate today = LocalDate.now();
        LocalDate expiryDate = today.plus(3, ChronoUnit.MONTHS);
        newUser.setPasswordExpiryDate(expiryDate);

        newUser.setIsActive(true);


        // Assign roles to the newUser object directly

        if (createDTO.getRoles() != null && !createDTO.getRoles().isEmpty()) {
            Set<roleModel> assignedRoles = createDTO.getRoles().stream()
                    .map(String::trim) // Ensure each role name is trimmed
                    .map(roleName -> roleRepo.findByName(roleName) // Use the injected roleRepo instance
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            newUser.setRoles(assignedRoles.stream().toList()); // Set the roles on the new userModel
        } else {

            throw new IllegalArgumentException("User must be assigned at least one role. Select one");
        }


        // Save user first
        // Save user (now with roles assigned)
        userModel savedUser = userRepo.save(newUser);

        // Create role-specific profiles
        roleServ.createRoleSpecificProfiles(savedUser, createDTO);
        // Return response DTO
        return mapToResponseDTO(savedUser);
    }

    /**
     * Authenticate user and return login response
     */
    public userLoginResponse authenticate(String email, String password) {
        String trimmedEmail = email.trim();
        Optional<userModel> userOpt = userRepo.findByEmail(trimmedEmail);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        userModel user = userOpt.get();

        // Check if user is active
        if (!user.getIsActive()) {
            throw new IllegalArgumentException("Account is deactivated");
        }

        // Verify password
        if (!passwordUtil.verify(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Check password expiry
        if (user.getPasswordExpiryDate() != null &&
                user.getPasswordExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Password has expired");
        }

        // Get user roles
        List<String> roles = roleServ.getUserRoles(user.getUserId());

        // Generate JWT token
        //String token = jwtUtil.generateToken(user.getUserId(), user.getEmail(), roles);

        // Create login response
        userLoginResponse response = new userLoginResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setSurname(user.getSurname());
        response.setRoles(roles);
        response.setLoginTime(OffsetDateTime.now());

        return response;
    }


    /**
     * Helper method to map userModel to userResponse
     */
    private userResponse mapToResponseDTO(userModel savedUser) {
        userResponse response = new userResponse();
        response.setUserId(savedUser.getUserId());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setSurname(savedUser.getSurname());
        response.setPhone(savedUser.getPhone());
        response.setIsActive(savedUser.getIsActive());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        // Map roles
        List<String> roles = savedUser.getRoles().stream()
                .map(roleModel::getName)
                .toList();
        response.setRoles(roles);

        return response;
    }
}
