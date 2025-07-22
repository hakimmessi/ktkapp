package com.ktk.ktkapp.dto.supplier;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class supplier {
    private Integer supplierId;
    private String name;
    private String contactName;
    private String contactPhone;
    private String country;
    private LocalDateTime createdAt;
}
