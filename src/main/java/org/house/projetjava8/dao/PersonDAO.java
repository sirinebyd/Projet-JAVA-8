package org.house.projetjava8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.house.projetjava8.model.Person;

public class PersonDAO {
    private final Connection connection;

    public PersonDAO() {
        this.connection = DatabaseManager.getConnection();
    }

    public void add(Person person) throws SQLException {
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
                        rs.getString("social_security_number"));
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
                            rs.getString("social_security_number"));
                }
            }
        }
        return null;
    }

    public Person getByName(String name) {
        String sql = "SELECT * FROM person WHERE first_name || ' ' || last_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Person(
                        rs.getInt("id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("gender"),
                        rs.getString("birth_date"),
                        rs.getString("birth_city"),
                        rs.getString("social_security_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        try (Connection conn = DatabaseManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return true; // en cas d'erreur, on bloque la suppression
        }
    }

    public void update(Person person) {
        String sql = "UPDATE person SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, birth_city = ?, social_security_number = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getGender());
            stmt.setString(4, person.getBirthDate());
            stmt.setString(5, person.getBirthCity());
            stmt.setString(6, person.getSocialSecurityNumber());
            stmt.setInt(7, person.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
