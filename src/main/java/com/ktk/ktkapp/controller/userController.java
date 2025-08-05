package com.ktk.ktkapp.controller;

import com.ktk.ktkapp.dto.user.request.userLoginRequest;
import com.ktk.ktkapp.dto.user.request.userRegistrationRequest;
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
    public ResponseEntity<userResponse> signUp(@Valid @RequestBody userRegistrationRequest registrationRequest) {
        userResponse createdUser = usrService.createUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<userLoginResponse> login(@Valid @RequestBody userLoginRequest loginRequest) {
        userLoginResponse userLoginResponse = usrService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(userLoginResponse);
    }



}
