package com.ktk.ktkapp.model.component;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "component")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class componentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "supplier_id")
    private Integer supplierId;

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "model", length = 100)
    private String model;

    @Column(name = "cost", precision = 10, scale = 2)
    private BigDecimal cost;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
