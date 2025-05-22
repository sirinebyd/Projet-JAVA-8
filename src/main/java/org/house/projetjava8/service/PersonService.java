package org.house.projetjava8.service;

import org.house.projetjava8.dao.PersonDao;
import org.house.projetjava8.model.Person;

import java.util.List;
import java.sql.SQLException;

public class PersonService {
    private final PersonDao dao = new PersonDao();

    public List<Person> getAll() {
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
    public boolean deletePersonIfPossible(int personId) {
    if (personDao.isPersonInUse(personId)) {
        throw new IllegalStateException("This person is linked to active occupations.");
    }
    return personDao.deletePerson(personId);
}

    public void delete(int id) {
        try {
            dao.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete: " + e.getMessage(), e);
        }
    }
}
