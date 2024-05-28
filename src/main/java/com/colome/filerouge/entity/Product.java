package com.colome.filerouge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    private Boolean status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Material material;

    @ManyToOne
    private Color color;
}
