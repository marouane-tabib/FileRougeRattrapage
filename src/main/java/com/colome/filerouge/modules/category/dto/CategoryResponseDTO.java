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

    private String sku;

    private String name;

    private String slug;

    private String description;

    private String price;

    private Integer quantity;

    private String category;

    private String brand;

    private String unit;

    private Long subCategoryId;

    private Long brandId;

    private Long categoryId;

    private Long unitId;

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
