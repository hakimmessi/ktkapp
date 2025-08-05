package com.ktk.ktkapp.dto.role.ktkEmployee.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ktkUserRequest {
    @Size(max = 100, message = "Department must not exceed 100 characters")
    private String department;
}
