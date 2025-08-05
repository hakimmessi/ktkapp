package com.ktk.ktkapp.dto.role.clientRep.response;

import lombok.*;

import java.time.OffsetDateTime;

/**
 * DTO for Kiosk Client Representative(s) profile data
 * Used when creating/updating KIOSK_CLIENT users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class kioskClientRepResponse {
    private Long clientRepId;
    private Long userId;
    private Long clientId;
    private String jobTitle;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


}
