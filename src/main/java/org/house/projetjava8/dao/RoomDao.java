package org.house.projetjava8.dao;

import org.house.projetjava8.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final Connection connection;

    public RoomDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    public void add(Room room) throws SQLException {
        String sql = "INSERT INTO room (name, gender_restriction, min_age, max_age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setString(2, room.getGenderRestriction());
            stmt.setInt(3, room.getMinAge());
            stmt.setInt(4, room.getMaxAge());
            stmt.executeUpdate();
        }
    }

    public List<Room> getAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("gender_restriction"),
                    rs.getInt("min_age"),
                    rs.getInt("max_age")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

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

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
