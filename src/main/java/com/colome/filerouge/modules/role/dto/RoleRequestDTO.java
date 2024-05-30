package com.colome.filerouge.modules.role.dto;

import com.colome.filerouge.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequestDTO {
    @NotNull(message = "Role name is required")
    private String roleName;

    // mapping to role
    public Role toRole() {
        return Role.builder()
                .name(this.roleName)
                .build();
    }
}
