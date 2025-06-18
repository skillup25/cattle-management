import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomePage {
    private Stage primaryStage;
    private FarmerManager farmerManager;

    public WelcomePage(Stage primaryStage, FarmerManager farmerManager) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
    }

    public void show() {
        // Welcome box setup
        VBox landingBox = new VBox(20);
        landingBox.setAlignment(Pos.CENTER);
        landingBox.setPadding(new Insets(40));
        landingBox.setPrefWidth(450); // Controls width of the welcome box itself

        // Style the welcome box with consistent colors and rounded borders
        landingBox.setStyle(
            "-fx-border-color: white; " +
            "-fx-border-width: 2; " +
            "-fx-background-color: #260101; " +
            "-fx-background-radius: 15; " +
            "-fx-border-radius: 15;"
        );

        // Welcome label
        Label welcomeLabel = new Label("Welcome to the Cattle Management System");
        welcomeLabel.setFont(new Font("Arial", 26));
        welcomeLabel.setTextFill(Color.WHITE);
        welcomeLabel.setStyle("-fx-font-weight: bold;");

        // Prompt label
        Label promptLabel = new Label("Would you like to register or login?");
        promptLabel.setFont(new Font("Arial", 18));
        promptLabel.setTextFill(Color.LIGHTGRAY);

        // Buttons
        Button registerButton = new Button("Register");
        Button loginButton = new Button("Login");
        registerButton.setPrefSize(200, 50);
        loginButton.setPrefSize(200, 50);
        registerButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        loginButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Button actions
        registerButton.setOnAction(e -> {
            RegistrationForm registrationForm = new RegistrationForm(primaryStage, farmerManager);
            registrationForm.show();
        });
        loginButton.setOnAction(e -> {
            LoginForm loginForm = new LoginForm(primaryStage, farmerManager);
            loginForm.show();
        });

        // Add components to the welcome box
        landingBox.getChildren().addAll(welcomeLabel, promptLabel, registerButton, loginButton);

        // Centering box in the scene
        StackPane root = new StackPane(landingBox); // StackPane centers its children
        root.setPadding(new Insets(50)); // Adds padding around the entire box to prevent it from touching edges
        root.setStyle("-fx-background-color: #260101;");

        // Scene setup
        Scene landingScene = new Scene(root, 800, 600);
        primaryStage.setTitle("Cattle Management System");
        primaryStage.setScene(landingScene);
        primaryStage.show();
    }
}
