package com.ktk.ktkapp.dto.role;

import com.ktk.ktkapp.dto.role.clientRep.request.kioskClientRepRequest;
import com.ktk.ktkapp.dto.role.clientRep.response.kioskClientRepResponse;
import com.ktk.ktkapp.dto.role.custodian.request.kioskCustodianRequest;
import com.ktk.ktkapp.dto.role.custodian.response.kioskCustodianResponse;
import com.ktk.ktkapp.dto.role.ktkEmployee.request.ktkUserRequest;
import com.ktk.ktkapp.dto.role.ktkEmployee.response.ktkUserResponse;
import com.ktk.ktkapp.model.role.kioskClientRepModel;
import com.ktk.ktkapp.model.role.kioskCustodianModel;
import com.ktk.ktkapp.model.role.ktkUserModel;
import org.springframework.stereotype.Component;

@Component
public class roleMapper {
    // --- KioskClientRep Mappings ---
    /**
     * Maps a KioskClientRepRequest DTO to a KioskClientRepModel entity.
     * Note that the user and client relationships are not set here;
     * they will be set in the service layer where the business logic resides.
     */
    public kioskClientRepModel toKioskClientRepModel(kioskClientRepRequest request) {
        kioskClientRepModel model = new kioskClientRepModel();
        model.setJobTitle(request.getJobTitle());
        return model;
    }

    /**
     * Maps a KioskClientRepModel entity to a KioskClientRepResponse DTO.
     * Extracts the IDs from the related User and Client entities.
     */
    public kioskClientRepResponse toKioskClientRepResponse(kioskClientRepModel model) {
        kioskClientRepResponse response = new kioskClientRepResponse();
        response.setClientRepId(model.getClientRepId());
        response.setUserId(model.getUser().getUserId());
        response.setClientId(model.getClient().getClientId());
        response.setJobTitle(model.getJobTitle());
        response.setCreatedAt(model.getUser().getCreatedAt());
        response.setUpdatedAt(model.getUser().getUpdatedAt());
        return response;
    }

    // --- KioskCustodian Mappings ---
    /**
     * Maps a KioskCustodianRequest DTO to a KioskCustodianModel entity.
     * The model is created here, but the relationships will be set in the service.
     */
    public kioskCustodianModel toKioskCustodianModel(kioskCustodianRequest request) {
        return new kioskCustodianModel();
    }

    /**
     * Maps a KioskCustodianModel entity to a KioskCustodianResponse DTO.
     * Extracts the IDs from the related User and Client entities.
     */
    public kioskCustodianResponse toKioskCustodianResponse(kioskCustodianModel model) {
        kioskCustodianResponse response = new kioskCustodianResponse();
        response.setCustodianId(model.getCustodianId());
        response.setUserId(model.getUser().getUserId());
        response.setClientId(model.getClient().getClientId());
        return response;
    }

    // --- KtkUser Mappings ---
    /**
     * Maps a KtkUserRequest DTO to a KtkUserModel entity.
     */
    public ktkUserModel toKtkUserModel(ktkUserRequest request) {
        ktkUserModel model = new ktkUserModel();
        model.setDepartment(request.getDepartment());
        return model;
    }

    /**
     * Maps a KtkUserModel entity to a KtkUserResponse DTO.
     * Extracts the user ID from the related User entity.
     */
    public ktkUserResponse toKtkUserResponse(ktkUserModel model) {
        ktkUserResponse response = new ktkUserResponse();
        response.setKtkId(model.getKtkId());
        response.setUserId(model.getUser().getUserId());
        response.setDepartment(model.getDepartment());
        response.setCreatedAt(model.getUser().getCreatedAt());
        response.setUpdatedAt(model.getUser().getUpdatedAt());
        return response;
    }
}