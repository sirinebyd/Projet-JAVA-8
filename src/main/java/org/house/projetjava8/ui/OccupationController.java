package org.house.projetjava8.ui;

import java.time.LocalDate;
import java.util.List;

import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Person;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.OccupationService;
import org.house.projetjava8.service.PersonService;
import org.house.projetjava8.service.RoomService;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OccupationController {

    private final OccupationService occupationService = new OccupationService();
    private final BedService bedService = new BedService();
    private final PersonService personService = new PersonService();
    private final RoomService roomService = new RoomService();

    @FXML
    private ComboBox<Person> personComboBox;
    @FXML
    private ComboBox<Bed> bedComboBox;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private CheckBox exitedCheckBox;

    @FXML
    private TableView<Occupation> occupationTable;
    @FXML
    private TableColumn<Occupation, String> colPerson;
    @FXML
    private TableColumn<Occupation, String> colBed;
    @FXML
    private TableColumn<Occupation, String> colStartDate;
    @FXML
    private TableColumn<Occupation, String> colEndDate;
    @FXML
    private TableColumn<Occupation, Boolean> colExited;

    @FXML
    public void initialize() {
        List<Person> personnes = PersonService.getAll();
        personComboBox.setItems(FXCollections.observableArrayList(personnes));
        
        // Configuration de la ComboBox des personnes
        personComboBox.setCellFactory(param -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                if (empty || person == null) {
                    setText(null);
                } else {
                    setText(person.getFirstName() + " " + person.getLastName());
                }
            }
        });
        
        personComboBox.setButtonCell(new ListCell<Person>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                if (empty || person == null) {
                    setText(null);
                } else {
                    setText(person.getFirstName() + " " + person.getLastName());
                }
            }
        });

        // Configuration de la ComboBox des lits
        bedComboBox.setCellFactory(param -> new ListCell<Bed>() {
            @Override
            protected void updateItem(Bed bed, boolean empty) {
                super.updateItem(bed, empty);
                if (empty || bed == null) {
                    setText(null);
                } else {
                    Room room = roomService.getById(bed.getRoomId());
                    setText(bed.getLabel() + " - " + (room != null ? room.getName() : "Salle inconnue"));
                }
            }
        });
        
        bedComboBox.setButtonCell(new ListCell<Bed>() {
            @Override
            protected void updateItem(Bed bed, boolean empty) {
                super.updateItem(bed, empty);
                if (empty || bed == null) {
                    setText(null);
                } else {
                    Room room = roomService.getById(bed.getRoomId());
                    setText(bed.getLabel() + " - " + (room != null ? room.getName() : "Salle inconnue"));
                }
            }
        });

        bedComboBox.setItems(FXCollections.observableArrayList(bedService.getAll()));

        // Configuration des colonnes de la table
        colPerson.setCellValueFactory(cellData -> {
            int personId = cellData.getValue().getPersonId();
            Person person = personnes.stream()
                .filter(p -> p.getId() == personId)
                .findFirst()
                .orElse(null);
            return new SimpleStringProperty(
                person != null ? person.getFirstName() + " " + person.getLastName() : "Inconnu"
            );
        });

        colBed.setCellValueFactory(cellData -> {
            int bedId = cellData.getValue().getBedId();
            Bed bed = bedService.getAll().stream()
                .filter(b -> b.getId() == bedId)
                .findFirst()
                .orElse(null);
            if (bed != null) {
                Room room = roomService.getById(bed.getRoomId());
                return new SimpleStringProperty(
                    bed.getLabel() + " - " + (room != null ? room.getName() : "Salle inconnue")
                );
            }
            return new SimpleStringProperty("Inconnu");
        });

        colStartDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(
                cellData.getValue().getStartDate() != null ? 
                cellData.getValue().getStartDate().toString() : ""
            )
        );

        colEndDate.setCellValueFactory(cellData -> 
            new SimpleStringProperty(
                cellData.getValue().getEndDate() != null ? 
                cellData.getValue().getEndDate().toString() : ""
            )
        );

        colExited.setCellValueFactory(cellData -> 
            new SimpleBooleanProperty(cellData.getValue().isExited())
        );

        refreshTable();
    }

    private void refreshTable() {
        List<Occupation> list = occupationService.getAll();
        ObservableList<Occupation> data = FXCollections.observableArrayList(list);
        occupationTable.setItems(data);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void onAddOccupation() {
        Person p = personComboBox.getValue();
        Bed b = bedComboBox.getValue();
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (p != null && b != null && start != null && end != null) {
            // Vérifier si la personne est déjà affectée à un lit
            List<Occupation> existingOccupations = occupationService.getAll().stream()
                .filter(o -> o.getPersonId() == p.getId() && !o.isExited())
                .toList();

            if (!existingOccupations.isEmpty()) {
                showAlert("Erreur d'affectation", 
                    "Cette personne est déjà affectée à un lit. Une personne ne peut être affectée qu'à un seul lit à la fois.");
                return;
            }

            // Vérifier la capacité du lit
            List<Occupation> bedOccupations = occupationService.getAll().stream()
                .filter(o -> o.getBedId() == b.getId() && !o.isExited())
                .toList();

            if (bedOccupations.size() >= 2) {
                showAlert("Erreur d'affectation", 
                    "Ce lit a déjà atteint sa capacité maximale (2 personnes).");
                return;
            }

            Occupation o = new Occupation();
            o.setPersonId(p.getId());
            o.setBedId(b.getId());
            o.setStartDate(start);
            o.setEndDate(end);
            o.setExited(exitedCheckBox.isSelected());

            occupationService.save(o);
            refreshTable();
            clearFields();
        }
    }

    private void clearFields() {
        personComboBox.setValue(null);
        bedComboBox.setValue(null);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        exitedCheckBox.setSelected(false);
    }

    @FXML
    public void onUpdateOccupation() {
        Occupation selected = occupationTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Person p = personComboBox.getValue();
            Bed b = bedComboBox.getValue();
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            if (p != null && b != null && start != null && end != null) {
                // Vérifier si la personne est déjà affectée à un autre lit
                List<Occupation> existingOccupations = occupationService.getAll().stream()
                    .filter(o -> o.getPersonId() == p.getId() && !o.isExited() && o.getId() != selected.getId())
                    .toList();

                if (!existingOccupations.isEmpty()) {
                    showAlert("Erreur de modification", 
                        "Cette personne est déjà affectée à un autre lit. Une personne ne peut être affectée qu'à un seul lit à la fois.");
                    return;
                }

                // Vérifier la capacité du lit si on change de lit
                if (b.getId() != selected.getBedId()) {
                    List<Occupation> bedOccupations = occupationService.getAll().stream()
                        .filter(o -> o.getBedId() == b.getId() && !o.isExited())
                        .toList();

                    if (bedOccupations.size() >= 2) {
                        showAlert("Erreur de modification", 
                            "Le lit sélectionné a déjà atteint sa capacité maximale (2 personnes).");
                        return;
                    }
                }

                selected.setPersonId(p.getId());
                selected.setBedId(b.getId());
                selected.setStartDate(start);
                selected.setEndDate(end);
                selected.setExited(exitedCheckBox.isSelected());

                occupationService.update(selected);
                refreshTable();
                clearFields();
            }
        }
    }

    @FXML
    public void onDeleteOccupation() {
        Occupation selected = occupationTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            occupationService.delete(selected);
            refreshTable();
            clearFields();
        }
    }
}
