package com.ktk.ktkapp.dto.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class component {
    private Integer componentId;
    private String name;
    private Integer supplierId;
    private String serialNumber;
    private String model;
    private BigDecimal cost;
    private LocalDateTime createdAt;
}
