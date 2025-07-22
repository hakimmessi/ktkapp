package com.ktk.ktkapp.dto.kiosk;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskLocation {
    private Integer locationId;
    private String locationName;
    private String physicalAddress;
    private String gpsCoordinates;
    private String locationType;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
