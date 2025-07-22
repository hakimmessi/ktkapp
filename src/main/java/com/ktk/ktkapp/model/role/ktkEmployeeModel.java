package com.ktk.ktkapp.model.role;

import com.ktk.ktkapp.model.user.userModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kinektek_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ktkEmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ktk_id")
    private Long ktkId;

    /** unique link to users.user_id */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private userModel user;

    @Column(length = 100)
    private String department;
}
