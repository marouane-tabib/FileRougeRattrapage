package com.colome.filerouge.modules.color.dto;

import com.colome.filerouge.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorResponseDTO {

    private Long id;

    private String name;

    private String status;

    // mapping from color
    public static ColorResponseDTO fromColor(Color color) {
        return ColorResponseDTO.builder()
                .id(color.getId())
                .name(color.getName())
                .status(color.getStatus() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
