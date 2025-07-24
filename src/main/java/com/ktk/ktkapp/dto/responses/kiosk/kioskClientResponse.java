package com.ktk.ktkapp.dto.responses.kiosk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskClientResponse {
    private Long clientId;
    private String clientName;
    private String address;
    private String city;
    private String province;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long clientRepId;
    private String clientRepJobTitle;
}
