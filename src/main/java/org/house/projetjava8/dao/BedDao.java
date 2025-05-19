package org.house.projetjava8.dao;

import org.house.projetjava8.model.Bed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BedDAO {
    private final Connection connection;

    public BedDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    public void add(Bed bed) throws SQLException {
        String sql = "INSERT INTO bed (room_id, is_available) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bed.getRoomId());
            stmt.setBoolean(2, bed.isAvailable());
            stmt.executeUpdate();
        }
    }

    public List<Bed> getAll() throws SQLException {
        List<Bed> beds = new ArrayList<>();
        String sql = "SELECT * FROM bed";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bed bed = new Bed(
                    rs.getInt("id"),
                    rs.getInt("room_id"),
                    rs.getBoolean("is_available")
                );
                beds.add(bed);
            }
        }
        return beds;
    }

    public Bed getById(int id) throws SQLException {
        String sql = "SELECT * FROM bed WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Bed(
                        rs.getInt("id"),
                        rs.getInt("room_id"),
                        rs.getBoolean("is_available")
                    );
                }
            }
        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM bed WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void updateAvailability(int id, boolean isAvailable) throws SQLException {
        String sql = "UPDATE bed SET is_available = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}
