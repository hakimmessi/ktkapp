package com.ktk.ktkapp.model.user;


import com.ktk.ktkapp.model.role.roleModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * An entity class represents a table in the ktk-app relational database
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Data

public class userModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, name = "password_hash", columnDefinition = "TEXT")
    private String passwordHash;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(length = 100)
    private String surname;

    @Column(length = 20)
    private String phone;

    @Column(name = "password_expiry_date")
    private LocalDate passwordExpiryDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<roleModel> roles = new ArrayList<>();
}
