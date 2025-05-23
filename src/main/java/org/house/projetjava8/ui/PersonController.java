package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.service.PersonService;

import java.time.LocalDate;

public class PersonController {
    private final PersonService service = new PersonService();

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField genderField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField birthCityField;
    @FXML
    private TextField ssnField;

    @FXML
    public void onSave() {
        Person person = new Person("Dupont", "Claire", "F", LocalDate.of(2000, 6, 12));
        person.setLastName(lastNameField.getText());
        person.setFirstName(firstNameField.getText());
        person.setGender(genderField.getText());
        person.setBirthDate(String.valueOf(birthDatePicker.getValue()));
        person.setBirthCity(birthCityField.getText());
        person.setSocialSecurityNumber(ssnField.getText());
        service.save(person);
    }
}
