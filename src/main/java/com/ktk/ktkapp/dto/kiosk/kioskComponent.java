package com.ktk.ktkapp.dto.kiosk;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class kioskComponent {
    private Integer kioskId;
    private Integer componentId;
    private LocalDateTime installedAt;
}
