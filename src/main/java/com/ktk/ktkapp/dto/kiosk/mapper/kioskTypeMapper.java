package com.ktk.ktkapp.dto.kiosk.mapper;

import com.ktk.ktkapp.dto.kiosk.requests.kioskTypeRequest;
import com.ktk.ktkapp.dto.kiosk.responses.kioskTypeResponse;
import com.ktk.ktkapp.model.kiosk.kioskTypeModel;

public class kioskTypeMapper {
    public static kioskTypeModel toModel(kioskTypeRequest request) {
        kioskTypeModel model = new kioskTypeModel();
        model.setKioskTypeName(request.getKioskTypeName());
        return model;
    }

    public static kioskTypeResponse toResponse(kioskTypeModel model) {
        return new kioskTypeResponse(
                model.getKioskTypeId(),
                model.getKioskTypeName()
        );
    }
}
