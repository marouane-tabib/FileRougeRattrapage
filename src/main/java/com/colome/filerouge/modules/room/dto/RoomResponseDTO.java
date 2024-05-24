package com.colome.filerouge.modules.room.dto;

import com.colome.filerouge.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponseDTO {

    private Long id;

    private String name;

    private String status;

    // mapping from room
    public static RoomResponseDTO fromRoom(Room room) {
        return RoomResponseDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .status(room.getStatus() ? "ACTIVE" : "INACTIVE")
                .build();
    }
}
