package org.house.projetjava8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.house.projetjava8.model.Bed;

public class BedDAO {
    private static Connection connection = null;

    public BedDAO() {
        connection = DatabaseManager.getConnection();
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

    public static List<Bed> getAll() throws SQLException {
        List<Bed> beds = new ArrayList<>();
        String sql = "SELECT * FROM bed";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bed bed = new Bed(
                        rs.getInt("id"),
                        rs.getString("label"),
                        rs.getInt("capacity"),
                        rs.getInt("room_id"));
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
                            rs.getInt("room_id"));
                }
            }
        }
        return null;
    }

    public Bed getByLabel(String label) {
        String sql = "SELECT * FROM bed WHERE label = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bed bed = new Bed();
                bed.setId(rs.getInt("id"));
                bed.setLabel(rs.getString("label"));
                bed.setCapacity(rs.getInt("capacity"));
                bed.setRoomId(rs.getInt("room_id"));
                return bed;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM bed WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return false;
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

    public static boolean isBedInUse(int bedId) {
        String sql = "SELECT COUNT(*) FROM occupations WHERE bed_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bedId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

}
