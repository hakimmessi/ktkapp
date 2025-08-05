package com.ktk.ktkapp.dto.role.clientRep.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
/**
 * DTO for Kiosk Client Representative(s) request data
 * Used when creating/updating KIOSK_CLIENT_REP users
 */
public class kioskClientRepRequest {
    @NotNull(message = "Client ID is required for a KIOSK_CLIENT_REP profile")
    private Long clientId;

    @Size(max = 100, message = "Job title must not exceed 100 characters")
    private String jobTitle;
}
