package org.house.projetjava8.service;

import org.house.projetjava8.model.Room;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    private static RoomService roomService;

    @BeforeAll
    static void setup() {
        roomService = new RoomService();
    }

    @Test
    void testAddAndGetRoom() {
        Room room = new Room();
        room.setName("Test Room");
        room.setGenderRestriction("ALL");
        room.setMinAge(0);
        room.setMaxAge(99);

        roomService.addRoom(room);

        List<Room> rooms = roomService.getAll();
        assertTrue(rooms.stream().anyMatch(r -> r.getName().equals("Test Room")));
    }
}
