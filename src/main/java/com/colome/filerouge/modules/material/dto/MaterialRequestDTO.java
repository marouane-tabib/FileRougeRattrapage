package com.colome.filerouge.modules.material.dto;

import com.colome.filerouge.entity.Material;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialRequestDTO {

    @NotNull(message = "Material name is required")
    private String name;

    @NotNull(message = "Status is required")
    private String status;

    // mapping to material
    public static Material toMaterial(MaterialRequestDTO materialRequestDTO) {
        return Material.builder()
                .name(materialRequestDTO.getName())
                .status(Objects.equals(materialRequestDTO.getStatus(), "Active"))
                .build();
    }

}
