package com.ktk.ktkapp.dto.user;

import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for user updates
 * Used for PUT/PATCH /users/{id}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userUpdate {
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Surname must not exceed 100 characters")
    private String surname;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate passwordExpiryDate;

    private Boolean isActive;

    // Role management (optional - for updating user roles)
    private List<String> roles;
}
