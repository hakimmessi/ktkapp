package com.ktk.ktkapp.dto.user.mapper;

import com.ktk.ktkapp.dto.user.request.userRegistrationRequest;
import com.ktk.ktkapp.dto.user.responses.userResponse;
import com.ktk.ktkapp.dto.user.responses.userLoginResponse;
import com.ktk.ktkapp.model.user.userModel;
import com.ktk.ktkapp.model.role.roleModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * A manual mapper to convert between User DTOs and the UserModel entity.
 * This is an alternative to using an automated tool like MapStruct,
 * and it's useful for understanding the underlying mapping logic.
 * It's implemented as a Spring Component so it can be injected into other services.
 */
@Component
public class userMapper {
    /**
     * Maps a UserRegistrationRequest DTO to a UserModel entity.
     * Note: This method does NOT set the password hash or roles.
     * These fields are handled separately in the service layer for security and
     * business logic reasons.
     *
     * @param request The DTO containing the user's registration details.
     * @return A UserModel entity with data populated from the DTO.
     */
    public userModel toUserModel(userRegistrationRequest request) {
        userModel usrModel = new userModel();
        usrModel.setEmail(request.getEmail().trim());
        usrModel.setFirstName(request.getFirstName());
        usrModel.setSurname(request.getSurname());
        usrModel.setPhone(request.getPhone());
        usrModel.setIsActive(true);
        // Set a default password expiry date.
        usrModel.setPasswordExpiryDate(LocalDate.now().plus(3, ChronoUnit.MONTHS));
        return usrModel;
    }

    /**
     * Maps a UserModel entity to a UserResponse DTO.
     * This DTO is used for public user profile lookups and registration responses,
     * so it does not contain sensitive data like the password hash or JWT.
     *
     * @param usrModel The UserModel entity from the database.
     * @return A UserResponse DTO with public user information.
     */
    public userResponse toUserResponse(userModel usrModel) {
        userResponse response = new userResponse();
        response.setUserId(usrModel.getUserId());
        response.setEmail(usrModel.getEmail());
        response.setFirstName(usrModel.getFirstName());
        response.setSurname(usrModel.getSurname());
        response.setPhone(usrModel.getPhone());
        response.setIsActive(usrModel.getIsActive());
        response.setCreatedAt(usrModel.getCreatedAt());
        response.setUpdatedAt(usrModel.getUpdatedAt());

        // Map roles from the UserModel to a list of role names.
        if (usrModel.getRoles() != null) {
            List<String> roleNames = usrModel.getRoles().stream()
                    .map(roleModel::getName)
                    .toList();
            response.setRoles(roleNames);
        }
        return response;
    }

    /**
     * Maps a UserModel entity to a UserLoginResponse DTO.
     * This DTO is specifically for a successful login and includes the JWT token.
     *
     * @param userModel The UserModel entity from the database.
     * @param jwtToken The generated JWT token for the user.
     * @return A UserLoginResponse DTO with public user information and the JWT token.
     */
    public userLoginResponse toUserLoginResponse(userModel userModel, String jwtToken) {
        userLoginResponse response = new userLoginResponse();
        response.setUserId(userModel.getUserId());
        response.setEmail(userModel.getEmail());
        response.setFirstName(userModel.getFirstName());
        response.setSurname(userModel.getSurname());
        response.setLoginTime(OffsetDateTime.now());
        response.setAccessToken(jwtToken);

        // Map roles from the UserModel.
        if (userModel.getRoles() != null) {
            List<String> roleNames = userModel.getRoles().stream()
                    .map(roleModel::getName)
                    .toList();
            response.setRoles(roleNames);
        }
        return response;
    }
}
