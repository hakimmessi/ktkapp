package com.ktk.ktkapp.model.technician;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.OffsetDateTime;

@Entity
@Table(name = "altron_hub")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class altronHubModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "altron_hub_id")
    private Integer hubId;

    @Column(name = "hub_name", length = 50)
    private String hubName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "province", length = 50)
    private String province;

    @Column(name = "gps_coordinates", length = 100)
    private String gpsCoordinates;

    @Column(name = "contact_name", length = 100)
    private String contactName;

    @Column(name = "contact_email", length = 50)
    private String contactEmail;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
