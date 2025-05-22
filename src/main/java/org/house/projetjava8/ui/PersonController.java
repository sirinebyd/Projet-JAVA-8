package org.house.projetjava8.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.service.PersonService;

import java.time.LocalDate;

public class PersonController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField cityField;
    @FXML private TextField socialNumberField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private DatePicker birthDatePicker;
    @FXML private TableView<Person> personTable;

    private final PersonService personService = new PersonService();

    @FXML
    public void initialize() {
        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female", "Other"));
        loadPersons();
    }

    @FXML
    private void onAddPerson() {
        try {
            Person person = new Person(
                firstNameField.getText(),
                lastNameField.getText(),
                genderComboBox.getValue(),
                birthDatePicker.getValue(),
                cityField.getText(),
                socialNumberField.getText()
            );
            personService.addPerson(person);
            loadPersons();
            clearFields();
        } catch (Exception e) {
            showAlert("Error adding person: " + e.getMessage());
        }
    }

    private void loadPersons() {
        personTable.setItems(FXCollections.observableArrayList(personService.getAllPersons()));
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        cityField.clear();
        socialNumberField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        birthDatePicker.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
