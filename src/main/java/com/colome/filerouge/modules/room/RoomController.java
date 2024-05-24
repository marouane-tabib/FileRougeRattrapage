package com.colome.filerouge.modules.room;

import com.colome.filerouge.entity.Room;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.room.dto.RoomRequestDTO;
import com.colome.filerouge.modules.room.dto.RoomResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {

    private final RoomServiceInterface roomService;

    public RoomController(RoomServiceInterface roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public ResponseEntity<?> getRoomService() {
        List<Room> rooms = roomService.getAllRooms();

        if(rooms.isEmpty()) {
            return ResponseMessage.notFound("No room found");
        }else {
            List<RoomResponseDTO> roomResponseDTOS = new ArrayList<>();
            for(Room room : rooms) {
                roomResponseDTOS.add(RoomResponseDTO.fromRoom(room));
            }

            return ResponseMessage.ok(roomResponseDTOS, "Success");
        }
    }

    //get room by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        // get the room by id
        Room room = roomService.getRoomById(id);

        // convert room to room response dto
        RoomResponseDTO roomResponseDTO = RoomResponseDTO.fromRoom(room);

        // return response
        return ResponseMessage.ok(roomResponseDTO, "Success");
    }

    // add room
    @PostMapping("/room")
    public ResponseEntity<?> addRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        // convert room request dto to room
        Room room = RoomRequestDTO.toRoom(roomRequestDTO);

        // save the room
        room = roomService.saveRoom(room);

        // convert room to room response dto
        RoomResponseDTO roomResponseDTO = RoomResponseDTO.fromRoom(room);

        // return response
        return ResponseMessage.created(roomResponseDTO, "Room added successfully");
    }

    // update room
    @PutMapping("/room/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody RoomRequestDTO roomRequestDTO, @PathVariable Long id) {
        // convert room request dto to room
        Room room = RoomRequestDTO.toRoom(roomRequestDTO);

        // update the room
        Room updatedRoom = roomService.updateRoom(id, room);

        // convert room to room response dto
        RoomResponseDTO roomResponseDTO = RoomResponseDTO.fromRoom(updatedRoom);

        // return response
        return ResponseMessage.ok(roomResponseDTO, "Room updated successfully");
    }

    // delete room
    @DeleteMapping("/room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        // delete the room
        roomService.deleteRoomById(id);

        // return response
        return ResponseMessage.ok(null,"Room deleted successfully");
    }
}
