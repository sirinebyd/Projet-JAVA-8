package org.house.projetjava8.service;

import org.house.projetjava8.dao.BedDAO;
import org.house.projetjava8.model.Bed;

import java.util.List;
import java.sql.SQLException;

public class BedService {
    private final BedDAO dao = new BedDAO();

    public static List<Bed> getBedsByRoom(int roomId) {
        try {
            BedDAO bedDAO = new BedDAO(); // ou utilise une instance si déjà créée
            List<Bed> allBeds = bedDAO.getAll();

            return allBeds.stream()
                    .filter(bed -> bed.getRoomId() == roomId)
                    .toList();

        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }


    public List<Bed> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to getAll: " + e.getMessage(), e);
        }
    }

    public Bed getById(int id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to getById: " + e.getMessage(), e);
        }
    }

    public void save(Bed bed) {
        try {
            dao.add(bed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save: " + e.getMessage(), e);
        }
    }

    public void update(Bed bed) {
        try {
            dao.update(bed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try {
            BedDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }
    public static boolean deleteBedIfPossible(int bedId) throws SQLException {
    if (BedDAO.isBedInUse(bedId)) {
        throw new IllegalStateException("This bed is currently in use.");
    }
    return BedDAO.delete(bedId);
}

    public List<Bed> getAllBeds() {
        try {
            return BedDAO.getAll(); // ou getAllBeds() selon ton DAO
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of(); // retourne une liste vide si erreur
        }
    }
}
