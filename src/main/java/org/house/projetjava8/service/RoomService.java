package org.house.projetjava8.service;

import org.house.projetjava8.dao.RoomDao;
import org.house.projetjava8.model.Room;

import java.util.List;
import java.sql.SQLException;

public class RoomService {
    private final RoomDao dao = new RoomDao();

    public List<Room> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to getAll: " + e.getMessage(), e);
        }
    }

    public Room getById(int id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to getById: " + e.getMessage(), e);
        }
    }

    public void save(Room room) {
        try {
            dao.add(room);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }
    public boolean deleteRoomIfPossible(int roomId) {
    List<Bed> beds = bedService.getBedsByRoom(roomId);
    for (Bed b : beds) {
        if (bedDao.isBedInUse(b.getId())) {
            throw new IllegalStateException("At least one bed in this room is occupied.");
        }
    }
    // Supprimer tous les lits avant la salle
    for (Bed b : beds) {
        bedDao.deleteBed(b.getId());
    }
    return roomDao.deleteRoom(roomId);
}
}
