package com.ktk.ktkapp.model.kiosk;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "kiosk_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kiosk_status_id")
    private Integer kioskStatusId;

    @Column(name = "kiosk_status_name", nullable = false, unique = true, length = 50)
    private String kioskStatusName;
}
