package com.ktk.ktkapp.dto.user.responses;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO for login response with user info and roles
 * Used as response for POST /auth/login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userLoginResponse {
    private Long userId;
    private String email;
    private String firstName;
    private String surname;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime loginTime;



}
