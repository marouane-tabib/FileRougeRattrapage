package com.colome.filerouge.modules.room;

import com.colome.filerouge.entity.Room;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements RoomServiceInterface {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room saveRoom(Room room) {
        // check the room name
        if (roomRepository.findByName(room.getName()).isPresent()) {
            throw new ResourceNotFoundException("Room with name " + room.getName() + " already exists");
        }

        // save room
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        // check the room name
        if (roomRepository.findByName(room.getName()).isPresent() && !roomRepository.findByName(room.getName()).get().getId().equals(id)) {
            throw new ResourceNotFoundException("Room with name " + room.getName() + " already exists");
        }

        // get room by id
        Room roomToUpdate = this.getRoomById(id);

        // set sku, slug, category, brand
        roomToUpdate.setName(room.getName());
        roomToUpdate.setDescription(room.getDescription());
        roomToUpdate.setPrice(room.getPrice());

        // save room
        return roomRepository.save(roomToUpdate);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Room with id " + id + " not found")
                );
    }

    @Override
    public void deleteRoomById(Long id) {
        Room room = this.getRoomById(id);
        roomRepository.delete(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
