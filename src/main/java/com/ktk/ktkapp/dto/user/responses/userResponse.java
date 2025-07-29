package com.ktk.ktkapp.dto.user.responses;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO for public user responses with roles
 * Used for GET /users/{id} and GET /users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class userResponse {
    private Long userId;
    private String email;
    private String firstName;
    private String surname;
    private String phone;
    private Boolean isActive;
    private List<String> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime updatedAt;
}
