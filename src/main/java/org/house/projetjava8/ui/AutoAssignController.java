package org.house.projetjava8.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.house.projetjava8.service.OccupancyRequestService;
import org.house.projetjava8.model.OccupancyRequest;
import org.house.projetjava8.model.Room;

import java.util.List;

public class AutoAssignController {

    @FXML
    private VBox roomResultsBox;

    @FXML
    private Label resultLabel;

    @FXML
    private Button assignButton;

    private final OccupancyRequestService requestService;

    public AutoAssignController() {
        // Tu peux injecter le DAO via un singleton ici si nécessaire
        this.requestService = new OccupancyRequestService();
    }

    @FXML
    public void initialize() {
        resultLabel.setText("Appuie sur 'Assigner' pour générer des propositions.");
    }

    @FXML
    private void handleAutoAssign() {
        // Exemple de requête simulée
        OccupancyRequest request = new OccupancyRequest(2, "F", "2025-06-01", "2025-06-10");
        List<Room> eligible = requestService.getEligibleRooms(request);

        roomResultsBox.getChildren().clear();

        for (Room room : eligible) {
            Label label = new Label("🏠 Chambre : " + room.getName() + " [" + room.getGenderRestriction() + "]");
            roomResultsBox.getChildren().add(label);
        }

        resultLabel.setText("✔ " + eligible.size() + " chambre(s) trouvée(s).");
    }
}

