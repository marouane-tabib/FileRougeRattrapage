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

    @NotNull(message = "Status is required")
    private String status;

    // mapping to room
    public static Room toRoom(RoomRequestDTO roomRequestDTO) {
        return Room.builder()
                .name(roomRequestDTO.getName())
                .status(Objects.equals(roomRequestDTO.getStatus(), "Active"))
                .build();
    }

}
