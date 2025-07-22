package com.ktk.ktkapp.dto.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO for password change operations
 * Used for POST /users/{id}/change-password
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class passwordUpdate {
    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters")
    private String newPassword;

    @NotBlank(message = "Password confirmation is required")
    private String confirmPassword;
}
