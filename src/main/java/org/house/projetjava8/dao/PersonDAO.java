package org.house.projetjava8.dao;

import org.house.projetjava8.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static Connection connection = null;

    public PersonDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    public static void add(Person person) throws SQLException {
        String sql = "INSERT INTO person (last_name, first_name, gender, birth_date, birth_city, social_security_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getLastName());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getGender());
            stmt.setString(4, person.getBirthDate());
            stmt.setString(5, person.getBirthCity());
            stmt.setString(6, person.getSocialSecurityNumber());
            stmt.executeUpdate();
        }
    }

    public List<Person> getAll() throws SQLException {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Person person = new Person(
                    rs.getInt("id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("gender"),
                    rs.getString("birth_date"),
                    rs.getString("birth_city"),
                    rs.getString("social_security_number")
                );
                people.add(person);
            }
        }
        return people;
    }

    public Person getById(int id) throws SQLException {
        String sql = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Person(
                        rs.getInt("id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("gender"),
                        rs.getString("birth_date"),
                        rs.getString("birth_city"),
                        rs.getString("social_security_number")
                    );
                }
            }
        }
        return null;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM person WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        return false;
    }
    public boolean isPersonInUse(int personId) {
    String sql = "SELECT COUNT(*) FROM occupations WHERE person_id = ?";
        DatabaseMetaData database = null;
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, personId);
        ResultSet rs = stmt.executeQuery();
        return rs.next() && rs.getInt(1) > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return true; // en cas d’erreur, on bloque la suppression
    }
}

}
