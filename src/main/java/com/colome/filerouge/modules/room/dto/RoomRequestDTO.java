package com.colome.filerouge.modules.room.dto;

import com.colome.filerouge.entity.Room;
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
public class RoomRequestDTO {

    @NotNull(message = "Room name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Sub Category Id is required")
    private Long subCategoryId;

    @NotNull(message = "Brand Id is required")
    private Long brandId;

    @NotNull(message = "Unit Id is required")
    private Long unitId;

    @NotNull(message = "Status is required")
    private String status;

    // mapping to room
    public static Room toRoom(RoomRequestDTO roomRequestDTO) {
        return Room.builder()
                .name(roomRequestDTO.getName())
                .description(roomRequestDTO.getDescription())
                .price(roomRequestDTO.getPrice())
                .status(Objects.equals(roomRequestDTO.getStatus(), "Active"))
                .build();
    }

}
