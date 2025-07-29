package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.user.responses.userLoginResponse;
import com.ktk.ktkapp.dto.user.responses.userResponse;
import com.ktk.ktkapp.service.userService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ktk.ktkapp.dto.user.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class userController {
    private final userService usrService;

    public userController(userService usrService) {
        this.usrService = usrService;
    }

    /**
     * User registration endpoint
     * Creates a new user with roles and profile data
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody userCreate createDTO) {
        try {
            userResponse createdUser = usrService.createUser(createDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody userLogin loginDTO) {
        userLoginResponse user = usrService.authenticate(
                loginDTO.getEmail(), loginDTO.getPassword());

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }



}
