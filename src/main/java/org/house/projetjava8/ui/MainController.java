package org.house.projetjava8.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane mainPane;

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleShowOccupants() {
        loadView("/org/house/projetjava8/PersonView.fxml");
    }

    @FXML
    private void handleShowRooms() {
        loadView("/org/house/projetjava8/RoomBedView.fxml");
    }

    @FXML
    private void handleShowAssignments() {
        loadView("/org/house/projetjava8/AutoAssignView.fxml");  // ou "OccupationView.fxml"
    }

    @FXML
    private void handleEtatCentre() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/house/projetjava8/EtatCentre.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("État du centre");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
