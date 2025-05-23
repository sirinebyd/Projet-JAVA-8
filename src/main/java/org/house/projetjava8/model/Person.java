package org.house.projetjava8.model;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private int id;
    private String lastName;
    private String firstName;
    private String gender;
    private String birthDate;
    private String birthCity;
    private String socialSecurityNumber;

    public Person() {}

    public Person(String lastName, String firstName, String gender, String birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Person(int id, String lastName, String firstName, String gender, String birthDate,
                  String birthCity, String socialSecurityNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.birthCity = birthCity;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        if (birthDate == null) return -1;
        return Period.between(LocalDate.parse(birthDate), LocalDate.now()).getYears();
    }


    public void setName(String nom) {
        this.firstName = nom;
    }

}
