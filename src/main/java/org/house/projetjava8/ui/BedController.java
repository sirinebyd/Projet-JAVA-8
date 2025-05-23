package org.house.projetjava8.ui;

import org.house.projetjava8.model.Bed;
import org.house.projetjava8.model.Room;
import org.house.projetjava8.service.BedService;
import org.house.projetjava8.service.RoomService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BedController {

    private final BedService bedService = new BedService();
    private final RoomService roomService = new RoomService();

    @FXML private TextField labelField;
    @FXML private Spinner<Integer> capacitySpinner;
    @FXML private ComboBox<Room> roomComboBox;

    @FXML private TableView<Bed> bedTable;
    @FXML private TableColumn<Bed, String> colLabel;
    @FXML private TableColumn<Bed, Integer> colCapacity;
    @FXML private TableColumn<Bed, String> colRoom;

    @FXML
    public void initialize() {
        colLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        colRoom.setCellValueFactory(new PropertyValueFactory<>("roomName")); // adapter si besoin

        capacitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 1));
        roomComboBox.setItems(FXCollections.observableArrayList(roomService.getAll()));

        bedTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                labelField.setText(newVal.getLabel());
                capacitySpinner.getValueFactory().setValue(newVal.getCapacity());
                roomComboBox.getSelectionModel().select(roomService.getById(newVal.getRoomId()));
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        ObservableList<Bed> beds = FXCollections.observableArrayList(bedService.getAll());
        bedTable.setItems(beds);
    }

    @FXML
    public void onAddBed() {
        Bed bed = new Bed();
        bed.setLabel(labelField.getText());
        bed.setCapacity(capacitySpinner.getValue());
        bed.setRoomId(roomComboBox.getValue().getId());
        bedService.save(bed);
        refreshTable();
    }

    @FXML
    public void onUpdateBed() {
        Bed selected = bedTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setLabel(labelField.getText());
            selected.setCapacity(capacitySpinner.getValue());
            selected.setRoomId(roomComboBox.getValue().getId());
            bedService.update(selected);
            refreshTable();
        }
    }

    @FXML
    public void onDeleteBed() {
        Bed selected = bedTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            bedService.delete(selected.getId());
            refreshTable();
        }
    }
}
