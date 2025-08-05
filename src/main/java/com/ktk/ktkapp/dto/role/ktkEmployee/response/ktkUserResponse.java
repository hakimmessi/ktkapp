package com.ktk.ktkapp.dto.role.ktkEmployee.response;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * DTO for Kinektek User profile data
 * Used when creating/updating KTK_USER users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ktkUserResponse {
    private Long ktkId;
    private Long userId;
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
