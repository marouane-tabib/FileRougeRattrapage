package com.colome.filerouge.modules.auth.dto;

import com.colome.filerouge.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequestDTO {
    @NotNull(message = "First name is required")
    String firstName;

    @NotNull(message = "Last name is required")
    String lastName;

    @NotNull(message = "Email is required")
    @Pattern(
            regexp = "^(.+)@(.+)$",
            message = "Email must be valid"
    )
    String email;

    @NotNull(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    String password;

    public User toUser(){
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
    }
}
