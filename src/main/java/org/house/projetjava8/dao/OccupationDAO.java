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

    public static List<Occupation> getOccupationsForBed(int bedId) {
        OccupationDAO dao = new OccupationDAO();
        return getOccupationsForBed(bedId);
    }


    public void add(Occupation occupation) throws SQLException {
        String sql = "INSERT INTO occupation (person_id, bed_id, start_date, end_date, has_left) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, occupation.getPersonId());
            stmt.setInt(2, occupation.getBedId());
            stmt.setDate(3, Date.valueOf(occupation.getStartDate()));
            stmt.setDate(4, Date.valueOf(occupation.getEndDate()));
            stmt.setBoolean(5, occupation.isHasLeft());
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
                    rs.getDate("end_date").toLocalDate(),
                    rs.getBoolean("has_left")
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
                        rs.getDate("end_date").toLocalDate(),
                        rs.getBoolean("has_left")
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

    public void updateHasLeft(int id, boolean hasLeft) throws SQLException {
        String sql = "UPDATE occupation SET has_left = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, hasLeft);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void updateHasLeft(Occupation occupation) throws SQLException {
        String sql = "UPDATE occupation SET has_left = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, occupation.isHasLeft());
            stmt.setInt(2, occupation.getId());
            stmt.executeUpdate();
        }
    }

}
