package com.colome.filerouge.modules.category.dto;

import com.colome.filerouge.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String status;

    // mapping from category
    public static CategoryResponseDTO fromCategory(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .status(category.getStatus() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
