package com.ktk.ktkapp.dto.kiosk.requests;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class kioskStatusRequest {

    @NotBlank(message = "Kiosk status name must not be empty")
    private String kioskStatusName;
}
