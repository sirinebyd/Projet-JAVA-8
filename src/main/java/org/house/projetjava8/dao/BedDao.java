package org.house.projetjava8.dao;

import org.house.projetjava8.model.Bed;
import java.sql.*;

public class BedDAO {
    private Connection conn;

    public BedDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Bed bed) throws SQLException {
        String sql = "INSERT INTO bed (id, roomId, isAvailable) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bed.getId());
        stmt.setInt(2, bed.getRoomId());
        stmt.setBoolean(3, bed.isAvailable());
        stmt.executeUpdate();
    }
}
