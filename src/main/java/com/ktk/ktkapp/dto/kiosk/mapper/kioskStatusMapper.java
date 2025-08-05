package com.ktk.ktkapp.dto.kiosk.mapper;

import com.ktk.ktkapp.dto.kiosk.requests.kioskStatusRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskStatusResponse;
import com.ktk.ktkapp.model.kiosk.kioskStatusModel;

public class kioskStatusMapper {
    public static kioskStatusModel toModel(kioskStatusRequest request) {
        kioskStatusModel model = new kioskStatusModel();
        model.setKioskStatusName(request.getKioskStatusName());
        return model;
    }

    public static kioskStatusResponse toResponse(kioskStatusModel model) {
        return new kioskStatusResponse(
                model.getKioskStatusId(),
                model.getKioskStatusName()
        );
    }
}
