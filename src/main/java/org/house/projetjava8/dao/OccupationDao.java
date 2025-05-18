package org.house.projetjava8.dao;

import org.house.projetjava8.model.Occupation;
import java.sql.*;
import java.time.LocalDate;

public class OccupationDAO {
    private Connection conn;

    public OccupationDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Occupation o) throws SQLException {
        String sql = "INSERT INTO occupation (id, personId, bedId, startDate, endDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, o.getId());
        stmt.setInt(2, o.getPersonId());
        stmt.setInt(3, o.getBedId());
        stmt.setDate(4, Date.valueOf(o.getStartDate()));
        stmt.setDate(5, Date.valueOf(o.getEndDate()));
        stmt.executeUpdate();
    }
}
