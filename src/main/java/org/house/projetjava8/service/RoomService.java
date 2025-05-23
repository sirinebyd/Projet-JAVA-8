package org.house.projetjava8.service;

import java.sql.SQLException;
import java.util.List;

import org.house.projetjava8.dao.BedDAO;
import org.house.projetjava8.dao.RoomDAO;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Room;

public class RoomService {
    private final RoomDAO roomDAO = new RoomDAO();

    /**
     * Récupère toutes les salles.
     */
    public List<Room> getAll() {
        try {
            return roomDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la récupération des salles: " + e.getMessage(), e);
        }
    }

    /**
     * Récupère une salle par son ID.
     */
    public Room getById(int id) {
        try {
            return roomDAO.getById(id);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la récupération de la salle: " + e.getMessage(), e);
        }
    }

    /**
     * Ajoute une nouvelle salle.
     * 
     * @return true si l'ajout a réussi
     */
    public boolean addRoom(Room room) {
        try {
            return roomDAO.add(room);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de l'ajout de la salle: " + e.getMessage(), e);
        }
    }

    /**
     * Supprime une salle après avoir vérifié et supprimé ses lits si possible.
     * 
     * @return true si la suppression a réussi
     */
    public boolean deleteRoom(int roomId) {
        try {
            // Vérifier que aucun lit n'est occupé
            List<Bed> beds = BedService.getByRoom(roomId);
            for (Bed bed : beds) {
                if (BedDAO.isBedInUse(bed.getId())) {
                    throw new IllegalStateException("Au moins un lit de cette salle est occupé.");
                }
            }
            // Supprimer les lits associés
            for (Bed bed : beds) {
                BedDAO.delete(bed.getId());
            }
            // Supprimer la salle
            return roomDAO.delete(roomId);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la suppression de la salle: " + e.getMessage(), e);
        }
    }

    public boolean updateRoom(Room room) {
        try {
            return roomDAO.update(room);
        } catch (SQLException e) {
            throw new RuntimeException("Échec de la mise à jour de la salle: " + e.getMessage(), e);
        }
    }

    public List<Bed> getBedsByRoom(int roomId) {
        return BedService.getByRoom(roomId);
    }

    public boolean isBedInUse(int bedId) {
        return BedDAO.isBedInUse(bedId);
    }

    public boolean deleteBed(int bedId) throws SQLException {
        return BedDAO.delete(bedId);
    }
}
