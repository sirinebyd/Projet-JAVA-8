package org.house.projetjava8.service;

import org.house.projetjava8.dao.RoomDAO;
import org.house.projetjava8.dao.DatabaseManager;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.model.OccupancyRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OccupancyRequestService {

    private final RoomDAO roomDAO;

    public OccupancyRequestService() {
        this.roomDAO = DatabaseManager.getRoomDAO();
    }

    public List<Room> getEligibleRooms(OccupancyRequest request) {
        List<Room> allRooms;
        List<Room> eligibleRooms = new ArrayList<>();

        try {
            allRooms = roomDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return eligibleRooms;
        }

        for (Room room : allRooms) {
            boolean ageOk = request.getMinAge() >= room.getMinAge() && request.getMaxAge() <= room.getMaxAge();
            boolean genderOk = room.getGenderRestriction().equalsIgnoreCase("Aucun") ||
                    room.getGenderRestriction().equalsIgnoreCase(request.getGender());

            if (ageOk && genderOk) {
                eligibleRooms.add(room);
            }
        }

        return eligibleRooms;
    }
}
