package com.ktk.ktkapp.dto.kiosk.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskResponse {
    private Integer kioskId;
    private String terminalId;
    private String assetNumber;
    private LocalDate dateOfInstallation;
    private OffsetDateTime lastMaintenance;
    private Integer kioskTypeId;
    private Integer kioskStatusId;
    private Integer altronHubId;
    private Integer clientId;
    private Integer custodianId;
    private Integer locationId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}