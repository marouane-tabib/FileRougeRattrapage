package com.colome.filerouge.modules.auth.dto;

import com.colome.filerouge.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDTO {
    @NotBlank(message = "Email is required")
    @Pattern(
            regexp = "^(.+)@(.+)$",
            message = "Email must be valid"
    )
    String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    String password;

    public User toUser(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
