package com.colome.filerouge.modules.material.dto;

import com.colome.filerouge.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialResponseDTO {

    private Long id;

    private String name;

    private String status;

    // mapping from material
    public static MaterialResponseDTO fromMaterial(Material material) {
        return MaterialResponseDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .status(material.getStatus() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
