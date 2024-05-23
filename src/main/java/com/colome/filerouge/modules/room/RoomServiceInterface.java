package com.colome.filerouge.modules.room;

import com.colome.filerouge.entity.Room;

import java.util.List;

public interface RoomServiceInterface {
    Room saveRoom(Room room);

    Room updateRoom(Long id, Room room);

    Room getRoomById(Long id);

    void deleteRoomById(Long id);
    
    List<Room> getAllRooms();
}
