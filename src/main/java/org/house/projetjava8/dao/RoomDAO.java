package org.house.projetjava8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.house.projetjava8.model.Room;

public class RoomDAO {
    private final Connection connection;

    public RoomDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    /** Ajoute une chambre, retourne true si OK */
    public boolean add(Room room) throws SQLException {
        String sql = "INSERT INTO room (name, gender_restriction, min_age, max_age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getGenderRestriction());
            stmt.setInt(3, room.getMinAge());
            stmt.setInt(4, room.getMaxAge());
            return stmt.executeUpdate() == 1;
        }
    }

    /** Récupère toutes les chambres */
    public List<Room> getAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(new Room(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender_restriction"),
                    rs.getInt("min_age"),
                    rs.getInt("max_age")
                ));
            }
        }
        return rooms;
    }

    /** Récupère une chambre par ID, ou null si non trouvée */
    public Room getById(int id) throws SQLException {
        String sql = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender_restriction"),
                        rs.getInt("min_age"),
                        rs.getInt("max_age")
                    );
                }
            }
        }
        return null;
    }

    /** Supprime une chambre par ID, retourne true si au moins 1 ligne supprimée */
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(Room room) throws SQLException {
        String sql = "UPDATE room SET name = ?, gender_restriction = ?, min_age = ?, max_age = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getGenderRestriction());
            stmt.setInt(3, room.getMinAge());
            stmt.setInt(4, room.getMaxAge());
            stmt.setInt(5, room.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}
