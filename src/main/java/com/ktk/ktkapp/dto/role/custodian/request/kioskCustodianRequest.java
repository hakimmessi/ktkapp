package com.ktk.ktkapp.dto.role.custodian.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class kioskCustodianRequest {
    @NotNull(message = "Client ID is required for a KIOSK_CUSTODIAN profile.")
    private Long clientId;
}
