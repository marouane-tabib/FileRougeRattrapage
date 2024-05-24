package com.colome.filerouge.modules.product.dto;

import com.colome.filerouge.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

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

    // mapping from product
    public static ProductResponseDTO fromProduct(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price( "$ " + product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getStatus() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
