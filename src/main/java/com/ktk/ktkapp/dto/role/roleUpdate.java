package com.ktk.ktkapp.dto.role;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for role management operations
 * Used for POST /users/{id}/roles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class roleUpdate {

    @NotEmpty(message = "Roles list cannot be empty")
    private List<String> roles;
    private String action;
}
