package org.house.projetjava8.dao;

import org.house.projetjava8.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List


public class PersonDAO {
    private Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    public void add(Person person) throws SQLException {
        String sql = "INSERT INTO person (id, name, age, gender) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, person.getId());
        stmt.setString(2, person.getName());
        stmt.setInt(3, person.getAge());
        stmt.setString(4, person.getGender());
        stmt.executeUpdate();
    }

    public List<Person> getAll() throws SQLException {
        List<Person> list = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            list.add(new Person(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender")
            ));
        }
        return list;
    }
}
