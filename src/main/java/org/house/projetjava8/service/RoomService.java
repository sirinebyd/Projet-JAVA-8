package org.house.projetjava8.service;

import org.house.projetjava8.dao.RoomDAO;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.dao.BedDAO;

import java.util.List;
import java.sql.SQLException;

import org.house.projetjava8.service.BedService;

public class RoomService {
    private final RoomDAO dao = new RoomDAO();
    private final BedService bedService = new BedService();
    private final BedDAO bedDao = new BedDAO()

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
    List<Bed> beds = BedService.getBedsByRoom(roomId);
    for (Bed b : beds) {
        if (BedDao.isBedInUse(b.getId())) {
            throw new IllegalStateException("At least one bed in this room is occupied.");
        }
    }
    // Supprimer tous les lits avant la salle
    for (Bed b : beds) {
        BedDao.deleteBed(b.getId());
    }
    return dao.deleteRoom(roomId);
}
}
