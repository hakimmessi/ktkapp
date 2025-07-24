package com.ktk.ktkapp.dto.role;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Kinektek User profile data
 * Used when creating/updating KTK_USER users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ktkUser {
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
}
