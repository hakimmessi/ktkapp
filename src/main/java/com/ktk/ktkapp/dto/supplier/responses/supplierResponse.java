package com.ktk.ktkapp.dto.supplier.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class supplierResponse {
    private Integer supplierId;
    private String name;
    private String contactName;
    private String contactPhone;
    private String country;
    private LocalDateTime createdAt;
}