package org.house.projetjava8.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.service.PersonService;

public class PersonController {
    private final PersonService service = new PersonService();

    @FXML private TextField nameField;

    @FXML
    public void onSave() {
        Person person = new Person();
        person.setName(nameField.getText());
        service.save(person);
    }
}
