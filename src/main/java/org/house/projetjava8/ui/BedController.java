package org.house.projetjava8.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.house.projetjava8.model.Bed;
import org.house.projetjava8.service.BedService;

public class BedController {

    @FXML private Spinner<Integer> roomIdSpinner;
    @FXML private Spinner<Integer> capacitySpinner;
    @FXML private TableView<Bed> bedTable;

    private final BedService bedService = new BedService();

    @FXML
    public void initialize() {
        roomIdSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
        capacitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2, 1));
        loadBeds();
    }

    @FXML
    private void onAddBed() {
        try {
            Bed bed = new Bed(roomIdSpinner.getValue(), capacitySpinner.getValue());
            bedService.addBed(bed);
            loadBeds();
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void loadBeds() {
        bedTable.setItems(FXCollections.observableArrayList(bedService.getAllBeds()));
    }

    private void showAlert(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }
}
