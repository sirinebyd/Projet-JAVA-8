package org.house.projetjava8.ui;

import java.time.LocalDate;
import java.util.List;

import org.house.projetjava8.model.AutoAssignment;
import org.house.projetjava8.service.AutoAssignService;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AutoAssignController {

    private final AutoAssignService assignService = new AutoAssignService();

    @FXML
    private Spinner<Integer> nbPersonSpinner;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private CheckBox sameRoomCheckBox;

    @FXML
    private TableView<AutoAssignment> propositionTable;
    @FXML
    private TableColumn<AutoAssignment, String> colAssignedPerson;
    @FXML
    private TableColumn<AutoAssignment, String> colAssignedBed;
    @FXML
    private TableColumn<AutoAssignment, String> colAssignedRoom;

    private List<AutoAssignment> currentAssignments;

    @FXML
    public void initialize() {
        nbPersonSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));

        colAssignedPerson.setCellValueFactory(data -> data.getValue().personNameProperty());
        colAssignedBed.setCellValueFactory(data -> data.getValue().bedLabelProperty());
        colAssignedRoom.setCellValueFactory(data -> data.getValue().roomNameProperty());
    }

    @FXML
    public void onSearch() {
        int nb = nbPersonSpinner.getValue();
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        boolean sameRoom = sameRoomCheckBox.isSelected();

        if (start != null && end != null && start.isBefore(end)) {
            currentAssignments = assignService.generateAssignments(nb, start, end, sameRoom);
            propositionTable.setItems(FXCollections.observableArrayList(currentAssignments));
        }
    }

    @FXML
    public void onConfirmAssignment() {
        if (currentAssignments != null && !currentAssignments.isEmpty()) {
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();
            assignService.saveAssignments(currentAssignments, start, end);
            propositionTable.getItems().clear();
        }
    }
}
