package org.house.projetjava8.service;

import org.house.projetjava8.model.Person;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private static PersonService personService;

    @BeforeAll
    static void setup() {
        personService = new PersonService();
    }

    @Test
    void testAddAndGetPerson() {
        Person person = new Person();
        person.setName("Alice");
        person.setGender("F");
        person.setBirthDate(LocalDate.of(2000, 1, 1));

        personService.addPerson(person);

        List<Person> all = personService.getAllPersons();
        assertTrue(all.stream().anyMatch(p -> p.getName().equals("Alice")));
    }
}
