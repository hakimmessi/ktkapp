package com.ktk.ktkapp.model.role;

import com.ktk.ktkapp.model.user.userModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kiosk_custodian")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskCustodianModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "custodian_id")
    private Long custodianId;

    /** unique link to users.user_id */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private userModel user;

    /** many custodians can relate to the same kiosk_client */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private kioskClientRepModel client;
}
