package org.house.projetjava8.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane contentPane;

    private void loadView(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/house/view/" + fxmlName));
            Node view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            showError("Erreur de chargement", "Impossible de charger la vue : " + fxmlName, e);
            e.printStackTrace();
        } catch (Exception e) {
            showError("Erreur inattendue", "Impossible de charger la vue : " + fxmlName, e);
            e.printStackTrace();
        }
    }

    private void showError(String title, String content, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        StringBuilder sb = new StringBuilder(content);
        if (e != null) {
            sb.append("\n\n").append(e.toString());
            for (StackTraceElement ste : e.getStackTrace()) {
                sb.append("\n    at ").append(ste.toString());
            }
        }
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }

    @FXML
    public void handleShowOccupants() {
        loadView("PersonView.fxml");
    }

    @FXML
    public void handleShowRooms() {
        loadView("RoomBedView.fxml");
    }

    @FXML
    public void handleShowBeds() {
        loadView("BedView.fxml");
    }

    @FXML
    public void handleShowAssignments() {
        loadView("OccupationView.fxml");
    }

    @FXML
    public void handleShowAutoAssign() {
        loadView("AutoAssignView.fxml");
    }

    @FXML
    public void handleShowEtatCentre() {
        loadView("EtatCentre.fxml");
    }
}

/*
 * }
 * 
 * @FXML
 * private void handleEtatCentre() {
 * try {
 * String resource = "/org/house/view/EtatCentre.fxml";
 * URL location = getClass().getResource(resource);
 * if (location == null) {
 * throw new IllegalArgumentException("FXML non trouvé : " + resource);
 * }
 * Parent root = FXMLLoader.load(location);
 * Stage stage = new Stage();
 * stage.setTitle("État du centre");
 * stage.setScene(new Scene(root));
 * stage.show();
 * } catch (IOException | IllegalArgumentException e) {
 * e.printStackTrace();
 * }
 * }
 * }
 */
