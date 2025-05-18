package org.house.projetjava8.model;
import java.time.LocalDate;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private int age;
    private String gender;

    public Person(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

}
