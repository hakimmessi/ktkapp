package com.ktk.ktkapp.service;

import com.ktk.ktkapp.dto.user.*;
import com.ktk.ktkapp.dto.user.request.*;
import com.ktk.ktkapp.dto.user.responses.*;
import com.ktk.ktkapp.dto.user.responses.userLoginResponse;
import com.ktk.ktkapp.dto.user.responses.userResponse;
import com.ktk.ktkapp.dto.user.mapper.userMapper;
import com.ktk.ktkapp.service.JwtService;
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
    private final userMapper mapper;
    private final JwtService jwtUtil;

    public userService(userRepository userRepo, roleService roleServ, roleRepository roleRepo, userMapper mapper, JwtService jwtUtil) {
        this.userRepo = userRepo;
        this.roleServ = roleServ;
        this.roleRepo = roleRepo;
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Create a new user with roles and profile data
     */
    @Transactional
    public userResponse createUser(userRegistrationRequest createDTO) {
        // Check if email already exists
        if (userRepo.findByEmail(createDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Map DTO to userModel
        userModel newUser = mapper.toUserModel(createDTO);

        // Hash the password
        newUser.setPasswordHash(passwordUtil.hash(createDTO.getPassword()));

        // Assign roles based on the incoming DTO
        if (createDTO.getRoles() != null && !createDTO.getRoles().isEmpty()){
            Set<roleModel> assignedRoles = createDTO.getRoles().stream()
                    .map(String::trim)
                    .map(roleName -> roleRepo.findByName(roleName)
                            .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
                    .collect(Collectors.toSet());
            newUser.setRoles(assignedRoles.stream().toList());
        } else {
            throw new IllegalArgumentException("User must be assigned at least one role. Select one");
        }

        // Save the user and create role-specific profiles.
        userModel savedUser = userRepo.save(newUser);
        roleServ.createRoleSpecificProfiles(savedUser, createDTO);


        return mapper.toUserResponse(savedUser);
    }

    /**
     * Authenticate user and return login response
     */
    public userLoginResponse authenticate(String email, String password) {
        String trimmedEmail = email.trim();
        userModel user = userRepo.findByEmail(trimmedEmail)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));




        if (!user.getIsActive()) {
            throw new IllegalArgumentException("Account is deactivated");
        }
        if (!passwordUtil.verify(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        if (user.getPasswordExpiryDate() != null &&
                user.getPasswordExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Password has expired");
        }

        List<String> roles = user.getRoles().stream()
                .map(roleModel::getName)
                .toList();

        String jwtToken = jwtUtil.generateToken(user.getEmail(), roles);

        // Create login response
        userLoginResponse response = new userLoginResponse();
        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setSurname(user.getSurname());
        response.setRoles(roles);
        response.setLoginTime(OffsetDateTime.now());

        return mapper.toUserLoginResponse(user, jwtToken);
    }
}
