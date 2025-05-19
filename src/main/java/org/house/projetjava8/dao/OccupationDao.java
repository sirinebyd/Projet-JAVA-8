package org.house.projetjava8.dao;

import org.house.projetjava8.model.Occupation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OccupationDAO {
    private final Connection connection;

    public OccupationDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    public void add(Occupation occupation) throws SQLException {
        String sql = "INSERT INTO occupation (person_id, bed_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, occupation.getPersonId());
            stmt.setInt(2, occupation.getBedId());
            stmt.setDate(3, Date.valueOf(occupation.getStartDate()));
            stmt.setDate(4, Date.valueOf(occupation.getEndDate()));
            stmt.executeUpdate();
        }
    }

    public List<Occupation> getAll() throws SQLException {
        List<Occupation> occupations = new ArrayList<>();
        String sql = "SELECT * FROM occupation";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Occupation occupation = new Occupation(
                    rs.getInt("id"),
                    rs.getInt("person_id"),
                    rs.getInt("bed_id"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                );
                occupations.add(occupation);
            }
        }
        return occupations;
    }

    public Occupation getById(int id) throws SQLException {
        String sql = "SELECT * FROM occupation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Occupation(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
                        rs.getInt("bed_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate()
                    );
                }
            }
        }
        return null;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM occupation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
