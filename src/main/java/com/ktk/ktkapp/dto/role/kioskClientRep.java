package com.ktk.ktkapp.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Kiosk Client Representative(s) profile data
 * Used when creating/updating KIOSK_CLIENT users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskClientRep {
    private Long userId;
    private Long clientId;
    private String jobTitle;
}
