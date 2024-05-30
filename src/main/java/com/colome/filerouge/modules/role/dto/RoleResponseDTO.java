package com.colome.filerouge.modules.role.dto;

import com.colome.filerouge.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private Long id;

    private String roleName;
    
    private String createdAt;

    public static RoleResponseDTO fromRole(Role role){
        return RoleResponseDTO.builder()
                .id(role.getId())
                .roleName(role.getName())
                .build(
        );
    }
}
