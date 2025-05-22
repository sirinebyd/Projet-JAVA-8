package org.house.projetjava8.service;

import org.house.projetjava8.dao.PersonDao;
import org.house.projetjava8.model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonService {

    private final PersonDao personDao = new PersonDao();

    public void addPerson(Person person) {
        try {
            personDao.addPerson(person);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to add person: " + e.getMessage(), e);
        }
    }

    public List<Person> getAllPersons() {
        try {
            return personDao.getAllPersons();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get persons: " + e.getMessage(), e);
        }
    }
}
