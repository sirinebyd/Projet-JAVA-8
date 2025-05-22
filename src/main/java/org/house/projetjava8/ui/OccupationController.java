package org.house.projetjava8.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.house.projetjava8.model.Occupation;
import org.house.projetjava8.model.OccupancyRequest;
import org.house.projetjava8.service.OccupationService;

import java.sql.SQLException;
import java.util.List;

public class OccupationController {

    @FXML private ComboBox<String> genre;
    @FXML private TextField nombrePersonnes;
    @FXML private DatePicker dateDebut;
    @FXML private DatePicker dateFin;

    @FXML private TableView<Occupation> tableOccupations;
    @FXML private TableColumn<Occupation, Integer> colBedId;
    @FXML private TableColumn<Occupation, String> colDebut;
    @FXML private TableColumn<Occupation, String> colFin;

    private final OccupationService occupationService = new OccupationService();

    @FXML
    public void initialize() {
        genre.getItems().addAll("Homme", "Femme", "Mixte");

        colBedId.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        colDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
    }

    @FXML
    public void handleGenererOccupations(ActionEvent event) {
        try {
            int nb = Integer.parseInt(nombrePersonnes.getText());
            String g = genre.getValue();
            String dDebut = dateDebut.getValue().toString();
            String dFin = dateFin.getValue().toString();

            OccupancyRequest request = new OccupancyRequest(nb, g, dDebut, dFin);
            List<Occupation> occupations = occupationService.genererOccupations(request);

            ObservableList<Occupation> observableOccupations = FXCollections.observableArrayList(occupations);
            tableOccupations.setItems(observableOccupations);

        } catch (Exception e) {
            showAlert("Erreur", "Merci de remplir tous les champs correctement.");
            e.printStackTrace();
        }
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 
