package com.colome.filerouge.modules.room;

import com.colome.filerouge.entity.Product;
import com.colome.filerouge.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {
    private RoomRepository roomRepository;
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomRepository = Mockito.mock(RoomRepository.class);
        roomService = new RoomService(roomRepository);
    }

    @Test
    void saveRoom() {
        Room room = Room.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(roomRepository.findByName("Tests fake name")).thenReturn(Optional.empty());
        Mockito.when(roomRepository.save(room)).thenReturn(room);

        Room savedRoom = roomService.saveRoom(room);
        assertNotNull(savedRoom);
        assertEquals("Tests fake name", savedRoom.getName());
    }

    @Test
    void updateRoom() {
        Room room = Room.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Room roomUpdated = Room.builder()
                .id(room.getId())
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(roomRepository.findByName("Tests fake name")).thenReturn(Optional.of(room));
        Mockito.when(roomRepository.findById(2L)).thenReturn(Optional.of(room));
        Mockito.when(roomRepository.save(roomUpdated)).thenReturn(roomUpdated);

        Room updatedRoom = roomService.updateRoom(2L, roomUpdated);
        assertNotNull(updatedRoom);
        assertEquals("Tests fake name", updatedRoom.getName());
    }

    @Test
    void getRoomById() {
        Room room = Room.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(roomRepository.findById(2L)).thenReturn(Optional.of(room));
        Room expectedRoom = roomService.getRoomById(2L);

        assertNotNull(expectedRoom);
        assertEquals("Tests fake name", expectedRoom.getName());
        assertEquals(true, expectedRoom.getStatus());
    }
    
    @Test
    void deleteRoomById() {  // Corrected method name
        Room room = Room.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(roomRepository.findById(2L)).thenReturn(Optional.of(room));
        roomService.deleteRoomById(2L);
        Mockito.verify(roomRepository).findById(2L);
        Mockito.verify(roomRepository).deleteById(2L);
    }

    @Test
    void getAllRooms() {
        Room room = Room.builder()
                .name("Tests fake name")
                .status(true).build();

        List<Room> roomList = List.of(room);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);

        List<Room> expectedRoomList = roomService.getAllRooms();
        assertNotNull(expectedRoomList);
        assertEquals(expectedRoomList.size(), roomList.size(), "Size should be 2");
        Mockito.verify(roomRepository).findAll();
    }
}