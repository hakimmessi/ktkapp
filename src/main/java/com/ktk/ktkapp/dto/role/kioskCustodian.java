package com.ktk.ktkapp.dto.role;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Kiosk Custodian profile data
 * Used when creating/updating KIOSK_CUSTODIAN users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskCustodian {
    @NotNull(message = "Client ID is required for custodian")
    private Long clientId;
}
