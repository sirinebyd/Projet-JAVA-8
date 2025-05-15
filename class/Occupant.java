import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Separator;

public class Occupant extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final String nom = "Jean Dupont";
    private final String genre = "Homme";
    private final int age = 38;
    private final String naissance = "24/01/1986";
    private final String lieu = "Paris";
    private final String salle = "Salle 5";
    private final String sejour = "14/04/2025 - 25/04/2025";
    private final String secu = "123456789";

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // === MENU LATÉRAL (réutilisé) ===
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #4DB8FF;");
        sidebar.setPrefWidth(250);

        Button homeBtn = new Button("🏠 Home");
        TextField searchField = new TextField("🔍 Rechercher");
        searchField.setMaxWidth(200);
        VBox salleList = new VBox(5);
        for (int i = 1; i <= 7; i++) {
            salleList.getChildren().add(new Label("Salle " + i + " - Description"));
        }
        salleList.setStyle("-fx-background-color: #eeeeee; -fx-padding: 10; -fx-background-radius: 20;");
        sidebar.getChildren().addAll(homeBtn, searchField, salleList);
        root.setLeft(sidebar);

        // === CONTENU CENTRAL ===
        VBox centerContent = new VBox(15);
        centerContent.setPadding(new Insets(30));
        centerContent.setAlignment(Pos.TOP_CENTER);

        // Image profil
        Image image = new Image("file:img/profile_placeholder.png", 120, 120, true, true);
        ImageView profileView = new ImageView(image);

        // Nom et titre
        Label nomLabel = new Label(nom);
        nomLabel.setFont(Font.font("Arial", 30));
        nomLabel.setStyle("-fx-font-weight: bold;");

        Label sousTitre = new Label(age + " ans  -  " + genre);
        sousTitre.setFont(Font.font("Arial", 18));

        Separator separator = new Separator();

        // Infos patient
        VBox infos = new VBox(12);
        infos.setPadding(new Insets(20));
        infos.setAlignment(Pos.CENTER_LEFT);
        infos.getChildren().addAll(
            new Label("Date de naissance : " + naissance),
            new Label("Ville de naissance : " + lieu),
            new Label("Salle : " + salle),
            new Label("Date de séjour : " + sejour),
            new Label("Numéro de sécurité sociale : " + secu)
        );
        for (javafx.scene.Node label : infos.getChildren()) {
            ((Label) label).setFont(Font.font("Arial", 18));
        }

        // Bouton modifier
        Button modifierBtn = new Button("Modifier");
        modifierBtn.setStyle("-fx-border-color: black; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 5 15 5 15;");
        modifierBtn.setAlignment(Pos.CENTER_RIGHT);

        BorderPane.setMargin(modifierBtn, new Insets(20, 40, 0, 0));
        root.setTop(modifierBtn);

        centerContent.getChildren().addAll(profileView, nomLabel, sousTitre, separator, infos);
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Fiche de l'occupant");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
