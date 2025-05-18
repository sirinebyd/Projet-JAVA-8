package org.house.projetjava8.dao;

import org.house.projetjava8.model.Room;
import java.sql.*;

public class RoomDAO {
    private Connection conn;

    public RoomDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Room room) throws SQLException {
        String sql = "INSERT INTO room (id, name, capacity, isMixed) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, room.getId());
        stmt.setString(2, room.getName());
        stmt.setInt(3, room.getCapacity());
        stmt.setBoolean(4, room.isMixed());
        stmt.executeUpdate();
    }
}
