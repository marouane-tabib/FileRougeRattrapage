package com.colome.filerouge.modules.product.dto;

import com.colome.filerouge.entity.*;
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
public class ProductRequestDTO {

    @NotNull(message = "Product name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Status is required")
    private String status;

    @NotNull(message = "Category Id is required")
    private Long categoryId;

    @NotNull(message = "Category Id is required")
    private Long roomId;

    @NotNull(message = "Category Id is required")
    private Long materialId;

    @NotNull(message = "Category Id is required")
    private Long colorId;

    // mapping to product
    public static Product toProduct(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .status(Objects.equals(productRequestDTO.getStatus(), "Active"))
                .category(
                        Category.builder()
                                .id(productRequestDTO.getCategoryId())
                                .build()
                )
                .room(
                        Room.builder()
                                .id(productRequestDTO.getRoomId())
                                .build()
                )
                .material(
                        Material.builder()
                                .id(productRequestDTO.getMaterialId())
                                .build()
                )
                .color(
                        Color.builder()
                                .id(productRequestDTO.getColorId())
                                .build()
                )
                .build();
    }

}
