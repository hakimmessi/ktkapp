package com.ktk.ktkapp.model.kiosk;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kiosk_status")
@Getter
@Setter
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
