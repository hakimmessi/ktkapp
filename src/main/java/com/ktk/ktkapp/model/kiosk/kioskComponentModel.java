package com.ktk.ktkapp.model.kiosk;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kiosk_component")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(kioskComponentModel.KioskComponentId.class)
public class kioskComponentModel {

    @Id
    @Column(name = "kiosk_id")
    private Integer kioskId;

    @Id
    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "installed_at")
    private LocalDateTime installedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KioskComponentId implements Serializable {
        private Integer kioskId;
        private Integer componentId;
    }
}
