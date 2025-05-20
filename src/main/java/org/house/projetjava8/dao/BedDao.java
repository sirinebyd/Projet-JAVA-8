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
        String sql = "INSERT INTO bed (label, capacity, room_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bed.getLabel());
            stmt.setInt(2, bed.getCapacity());
            stmt.setInt(3, bed.getRoomId());
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
                    rs.getString("label"),
                    rs.getInt("capacity"),
                    rs.getInt("room_id")
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
                        rs.getString("label"),
                        rs.getInt("capacity"),
                        rs.getInt("room_id")
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

    public void update(Bed bed) throws SQLException {
        String sql = "UPDATE bed SET label = ?, capacity = ?, room_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, bed.getLabel());
            stmt.setInt(2, bed.getCapacity());
            stmt.setInt(3, bed.getRoomId());
            stmt.setInt(4, bed.getId());
            stmt.executeUpdate();
        }
    }
}
