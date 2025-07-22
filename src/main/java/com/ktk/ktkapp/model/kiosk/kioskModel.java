package com.ktk.ktkapp.model.kiosk;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "kiosk")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kiosk_id")
    private Integer kioskId;

    @Column(name = "terminal_id", length = 100)
    private String terminalId;

    @Column(name = "asset_number", length = 100)
    private String assetNumber;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "date_of_installation")
    private LocalDate dateOfInstallation;

    @Column(name = "last_maintenance")
    private OffsetDateTime lastMaintenance;

    @Column(name = "kiosk_type_id")
    private Integer kioskTypeId;

    @Column(name = "kiosk_status_id")
    private Integer kioskStatusId;

    @Column(name = "altron_hub_id")
    private Integer altronHubId;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "custodian_id")
    private Integer custodianId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
