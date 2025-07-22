package com.ktk.ktkapp.model.role;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class roleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
