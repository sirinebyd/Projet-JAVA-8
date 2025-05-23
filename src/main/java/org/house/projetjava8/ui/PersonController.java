package org.house.projetjava8.ui;

import java.util.List;

import org.house.projetjava8.model.Person;
import org.house.projetjava8.service.PersonService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonController {

    private final PersonService service = new PersonService();

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField cityField;
    @FXML
    private TextField socialNumberField;

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> colFirstName;
    @FXML
    private TableColumn<Person, String> colLastName;
    @FXML
    private TableColumn<Person, String> colGender;
    @FXML
    private TableColumn<Person, String> colBirthDate;
    @FXML
    private TableColumn<Person, String> colBirthCity;
    @FXML
    private TableColumn<Person, String> colSocialNumber;

    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("Homme", "Femme", "Autre");

        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colBirthCity.setCellValueFactory(new PropertyValueFactory<>("birthCity"));
        colSocialNumber.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        refreshTable();
    }

    private void refreshTable() {
        List<Person> allPeople = PersonService.getAll();
        ObservableList<Person> people = FXCollections.observableArrayList(allPeople);
        personTable.setItems(people);
    }

    @FXML
    public void onAddPerson() {
        Person person = new Person();
        person.setFirstName(firstNameField.getText());
        person.setLastName(lastNameField.getText());
        person.setGender(genderComboBox.getValue());
        person.setBirthDate(birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "");
        person.setBirthCity(cityField.getText());
        person.setSocialSecurityNumber(socialNumberField.getText());

        service.save(person);
        refreshTable();
        clearFields();
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        genderComboBox.setValue(null);
        birthDatePicker.setValue(null);
        cityField.clear();
        socialNumberField.clear();
    }

    @FXML
    public void onUpdatePerson() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setFirstName(firstNameField.getText());
            selected.setLastName(lastNameField.getText());
            selected.setGender(genderComboBox.getValue());
            selected.setBirthDate(birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "");
            selected.setBirthCity(cityField.getText());
            selected.setSocialSecurityNumber(socialNumberField.getText());

            service.update(selected);
            refreshTable();
            clearFields();
        }
    }

    @FXML
    public void onDeletePerson() {
        Person selected = personTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.delete(selected.getId());
            refreshTable();
            clearFields();
        }
    }
}
