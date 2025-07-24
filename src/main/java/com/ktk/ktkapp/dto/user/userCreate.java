package com.ktk.ktkapp.dto.user;


import com.ktk.ktkapp.dto.role.kioskClientRep;
import com.ktk.ktkapp.dto.role.kioskCustodian;
import com.ktk.ktkapp.dto.role.ktkUser;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * DTO for user registration/creation with role assignment
 * Used for POST /users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userCreate {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Surname must not exceed 100 characters")
    private String surname;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number is invalid")
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;

    @NotEmpty(message = "At least one role must be assigned")
    private List<String> roles; // e.g. ["KTK_USER", "KIOSK_CLIENT"]

    private kioskClientRep kioskClientRepProfile;
    private ktkUser ktkUser;
    private kioskCustodian kioskCustodianProfile;
}
