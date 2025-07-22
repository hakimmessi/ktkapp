package com.ktk.ktkapp.dto.technician;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class altronHub {
    private Integer altronHubId;
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
