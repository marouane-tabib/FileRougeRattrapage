package com.colome.filerouge.modules.auth.dto;

import com.colome.filerouge.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String token;
    private String role;

    public static AuthResponseDTO fromAuthResponseDTO(User user, String token) {
        return AuthResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .token(token)
                .role(user.getRole().getName())
                .build();
    }
}
