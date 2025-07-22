package com.ktk.ktkapp.dto.kiosk;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class kioskClient {
    private Long clientId;
    private Long clientRepId;
    private String clientName;
    private String address;
    private String city;
    private String province;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
