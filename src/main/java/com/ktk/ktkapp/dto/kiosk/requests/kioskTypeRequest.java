package com.ktk.ktkapp.dto.kiosk.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class kioskTypeRequest {

    @NotBlank(message = "Kiosk type name must not be empty")
    private String kioskTypeName;
}
