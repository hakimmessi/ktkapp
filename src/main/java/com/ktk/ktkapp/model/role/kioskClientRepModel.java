package com.ktk.ktkapp.model.role;

import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import com.ktk.ktkapp.model.user.userModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "kiosk_client_rep")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskClientRepModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_rep_id")
    private Long clientRepId;

    /** one‑to‑one with users.user_id */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private userModel user;

    /** many-to-one with kiosk_client.client_id */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private kioskClientModel client;

    @Column(name = "job_title", length = 100)
    private String jobTitle;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    void touchUpdatedAt() { updatedAt = OffsetDateTime.now(); }
}
