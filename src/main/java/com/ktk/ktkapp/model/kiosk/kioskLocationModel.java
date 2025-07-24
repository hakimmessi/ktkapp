package com.ktk.ktkapp.model.kiosk;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "kiosk_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskLocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "location_name", nullable = false, length = 100)
    private String locationName;

    @Column(name = "physical_address", length = 255)
    private String physicalAddress;

    @Column(name = "gps_coordinates", length = 100)
    private String gpsCoordinates;

    @Column(name = "location_type", length = 50)
    private String locationType;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
