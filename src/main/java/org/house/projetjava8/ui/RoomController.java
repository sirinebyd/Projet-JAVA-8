package org.house.projetjava8.ui;

import java.time.LocalDate;
import java.util.List;

import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.OccupationService;
import org.house.projetjava8.service.RoomService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class RoomController {

    private final RoomService roomService = new RoomService();
    private final OccupationService occupationService = new OccupationService();

    @FXML
    private TableView<Room> roomTable;
    @FXML
    private TableColumn<Room, String> colName;
    @FXML
    private TableColumn<Room, String> colGenderRestriction;
    @FXML
    private TableColumn<Room, Integer> colMinAge;
    @FXML
    private TableColumn<Room, Integer> colMaxAge;

    @FXML
    private TextField roomNameField;
    @FXML
    private ComboBox<String> genderRestrictionBox;
    @FXML
    private Spinner<Integer> minAgeSpinner;
    @FXML
    private Spinner<Integer> maxAgeSpinner;

    @FXML
    private FlowPane bedPane;

    @FXML
    public void initialize() {
        // Configuration des colonnes
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGenderRestriction.setCellValueFactory(new PropertyValueFactory<>("genderRestriction"));
        colMinAge.setCellValueFactory(new PropertyValueFactory<>("minAge"));
        colMaxAge.setCellValueFactory(new PropertyValueFactory<>("maxAge"));

        // Configuration de la ComboBox
        genderRestrictionBox.getItems().addAll("Aucune", "M", "F");
        genderRestrictionBox.setValue("Aucune");

        // Configuration des Spinners
        minAgeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120, 0));
        maxAgeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120, 99));

        // Écouteur de sélection
        roomTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayBedsForRoom(newVal);
            } else {
                bedPane.getChildren().clear();
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        ObservableList<Room> rooms = FXCollections.observableArrayList(roomService.getAll());
        roomTable.setItems(rooms);
    }

    @FXML
    public void onAddRoom() {
        try {
            Room room = new Room();
            room.setName(roomNameField.getText());
            room.setGenderRestriction(genderRestrictionBox.getValue());
            room.setMinAge(minAgeSpinner.getValue());
            room.setMaxAge(maxAgeSpinner.getValue());

            roomService.addRoom(room);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            showError("Erreur lors de l'ajout de la salle", e.getMessage());
        }
    }

    @FXML
    public void onUpdateRoom() {
        try {
            Room selected = roomTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setName(roomNameField.getText());
                selected.setGenderRestriction(genderRestrictionBox.getValue());
                selected.setMinAge(minAgeSpinner.getValue());
                selected.setMaxAge(maxAgeSpinner.getValue());

                roomService.updateRoom(selected);
                refreshTable();
                clearFields();
            }
        } catch (Exception e) {
            showError("Erreur lors de la mise à jour de la salle", e.getMessage());
        }
    }

    @FXML
    public void onDeleteRoom() {
        try {
            Room selected = roomTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                roomService.deleteRoom(selected.getId());
                refreshTable();
                clearFields();
            }
        } catch (Exception e) {
            showError("Erreur lors de la suppression de la salle", e.getMessage());
        }
    }

    private void displayBedsForRoom(Room room) {
        bedPane.getChildren().clear();
        List<Bed> beds = BedService.getByRoom(room.getId());
        List<Occupation> occupations = occupationService.getAll();
        LocalDate today = LocalDate.now();
        for (Bed bed : beds) {
            VBox bedBox = new VBox(5);
            bedBox.setStyle("-fx-border-color: black; -fx-padding: 5;");
            bedBox.setPrefSize(60, 60);

            Rectangle bedShape = new Rectangle(50, 20);
            boolean occupe = isBedOccupied(bed.getId(), occupations, today);
            bedShape.setFill(occupe ? Color.RED : Color.LIMEGREEN);

            Text label = new Text(bed.getLabel());
            bedBox.getChildren().addAll(label, bedShape);
            bedPane.getChildren().add(bedBox);
        }
    }

    private boolean isBedOccupied(int bedId, List<Occupation> occupations, LocalDate today) {
        for (Occupation occ : occupations) {
            if (occ.getBedId() == bedId && !occ.isHasLeft() &&
                    (occ.getStartDate() == null || !today.isBefore(occ.getStartDate())) &&
                    (occ.getEndDate() == null || !today.isAfter(occ.getEndDate()))) {
                return true;
            }
        }
        return false;
    }

    private void clearFields() {
        roomNameField.clear();
        genderRestrictionBox.setValue("Aucune");
        minAgeSpinner.getValueFactory().setValue(0);
        maxAgeSpinner.getValueFactory().setValue(99);
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
