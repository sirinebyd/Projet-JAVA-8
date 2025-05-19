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
        String sql = "INSERT INTO room (name, capacity, is_mixed) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getCapacity());
            stmt.setBoolean(3, room.isMixed());
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
                    rs.getInt("capacity"),
                    rs.getBoolean("is_mixed")
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
                        rs.getInt("capacity"),
                        rs.getBoolean("is_mixed")
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
