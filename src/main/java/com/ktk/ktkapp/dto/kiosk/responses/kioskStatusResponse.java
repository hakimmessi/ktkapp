package com.ktk.ktkapp.dto.kiosk.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskStatusResponse {
    private Integer kioskStatusId;
    private String kioskStatusName;
}
