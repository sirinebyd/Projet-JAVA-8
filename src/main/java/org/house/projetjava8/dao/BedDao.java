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
        String sql = "INSERT INTO bed (room_id, capacity) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bed.getRoomId());
            stmt.setInt(2, bed.getCapacity());
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
                    rs.getInt("capacity")
                );
                beds.add(bed);
            }
        }
        return beds;
    }
}
