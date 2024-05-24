package com.colome.filerouge.modules.color.dto;

import com.colome.filerouge.entity.Color;
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
public class ColorRequestDTO {

    @NotNull(message = "Color name is required")
    private String name;

    @NotNull(message = "Status is required")
    private String status;

    // mapping to color
    public static Color toColor(ColorRequestDTO colorRequestDTO) {
        return Color.builder()
                .name(colorRequestDTO.getName())
                .status(Objects.equals(colorRequestDTO.getStatus(), "Active"))
                .build();
    }

}
