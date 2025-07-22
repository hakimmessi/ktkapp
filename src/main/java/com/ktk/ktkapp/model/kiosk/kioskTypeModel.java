package com.ktk.ktkapp.model.kiosk;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "kiosk_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kiosk_type_id")
    private Integer kioskTypeId;

    @Column(name = "kiosk_type_name", nullable = false, unique = true, length = 50)
    private String kioskTypeName;
}
