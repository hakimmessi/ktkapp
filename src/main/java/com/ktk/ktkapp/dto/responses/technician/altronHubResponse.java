package com.ktk.ktkapp.dto.responses.technician;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class altronHubResponse {
    private Integer hubId;
    private String hubName;
    private String address;
    private String city;
    private String province;
    private String gpsCoordinates;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}