package org.house.projetjava8.service;

import java.sql.SQLException;
import java.util.List;

import org.house.projetjava8.dao.PersonDAO;
import org.house.projetjava8.model.Person;

public class PersonService {
    private static final PersonDAO dao = new PersonDAO();

    public static List<Person> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to getAll: " + e.getMessage(), e);
        }
    }

    public Person getById(int id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to getById: " + e.getMessage(), e);
        }
    }

    public void save(Person person) {
        try {
            dao.add(person);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save: " + e.getMessage(), e);
        }
    }



    public static boolean deletePersonIfPossible(int personId) throws SQLException {
    if (dao.isPersonInUse(personId)) {
        throw new IllegalStateException("This person is linked to active occupations.");
    }
    return dao.delete(personId);
}

    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }

    public void add(Person person) {
        try {
            dao.add(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPerson(Person p) {
        try {
            dao.add(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(Person person) {
        dao.update(person);
    }
}
