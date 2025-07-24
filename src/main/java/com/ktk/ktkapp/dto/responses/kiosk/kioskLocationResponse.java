package com.ktk.ktkapp.dto.responses.kiosk;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskLocationResponse {
    private Integer locationId;
    private String locationName;
    private String physicalAddress;
    private String gpsCoordinates;
    private String locationType;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
