package org.house.projetjava8.service;

import org.house.projetjava8.dao.RoomDAO;
import org.house.projetjava8.model.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public List<Room> getAllRooms() throws SQLException {
        return roomDAO.getAllRooms();
    }

    public Room getRoomById(int id) throws SQLException {
        return roomDAO.getRoomById(id);
    }
}