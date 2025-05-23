package org.house.projetjava8.service;

import org.house.projetjava8.model.Person;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    public static PersonService personService;

    @BeforeAll
    static void setup() {
        personService = new PersonService();
    }

    @Test
    void testAddAndGetPerson() {
        Person person = new Person("Dupont", "Claire", "F", LocalDate.of(2000, 6, 12));
        person.setFirstName("Alice");
        person.setGender("F");
        person.setBirthDate(String.valueOf(LocalDate.of(2000, 1, 1)));

        personService.add(person);

        List<Person> all = personService.getAll();
        assertTrue(all.stream().anyMatch(p -> p.getName().equals("Alice")));
    }
}
