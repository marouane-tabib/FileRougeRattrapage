package com.colome.filerouge.modules.category.dto;

import com.colome.filerouge.entity.Category;
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
public class CategoryRequestDTO {

    @NotNull(message = "Category name is required")
    private String name;

    @NotNull(message = "Status is required")
    private String status;

    // mapping to category
    public static Category toCategory(CategoryRequestDTO categoryRequestDTO) {
        return Category.builder()
                .name(categoryRequestDTO.getName())
                .status(Objects.equals(categoryRequestDTO.getStatus(), "Active"))
                .build();
    }

}
