import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegistrationForm {
    private Stage primaryStage;
    private FarmerManager farmerManager;

    public RegistrationForm(Stage primaryStage, FarmerManager farmerManager) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
    }

    public void show() {
        VBox registrationBox = new VBox(20);
        registrationBox.setAlignment(Pos.CENTER);
        registrationBox.setPadding(new Insets(30));
        registrationBox.setStyle("-fx-background-color: #260101;");

        Label titleLabel = new Label("Register a New Account");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        // Set fixed width and height for all fields and buttons
        double fieldWidth = 350; // Preferred width
        double fieldHeight = 50; // Preferred height
        double buttonWidth = 150; // Preferred width for the button

        // Create the TextField for Name
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-value-color:");
        nameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        nameField.setPrefSize(fieldWidth, fieldHeight); // Set preferred size
        nameField.setMinSize(fieldWidth, fieldHeight); // Set minimum size
        nameField.setMaxSize(fieldWidth, fieldHeight); // Set maximum size

        // Create the ComboBox for Farm Location
        ComboBox<String> locationComboBox = new ComboBox<>();
        locationComboBox.getItems().addAll(
            "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
            "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala",
            "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland",
            "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
            "Uttar Pradesh", "Uttarakhand", "West Bengal"
        );
        locationComboBox.setPromptText("Select Farm Location");
        locationComboBox.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        locationComboBox.setPrefSize(fieldWidth, fieldHeight); // Set preferred size
        locationComboBox.setMinSize(fieldWidth, fieldHeight); // Set minimum size
        locationComboBox.setMaxSize(fieldWidth, fieldHeight); // Set maximum size

        // Create the TextField for Username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        usernameField.setPrefSize(fieldWidth, fieldHeight); // Set preferred size
        usernameField.setMinSize(fieldWidth, fieldHeight); // Set minimum size
        usernameField.setMaxSize(fieldWidth, fieldHeight); // Set maximum size

        // Create the PasswordField for Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        passwordField.setPrefSize(fieldWidth, fieldHeight); // Set preferred size
        passwordField.setMinSize(fieldWidth, fieldHeight); // Set minimum size
        passwordField.setMaxSize(fieldWidth, fieldHeight); // Set maximum size

        // Create the Register Button
        Button registerButton = new Button("Register");
        registerButton.setPrefSize(buttonWidth, fieldHeight); // Set preferred size for the button
        registerButton.setMinSize(buttonWidth, fieldHeight); // Set minimum size for the button
        registerButton.setMaxSize(buttonWidth, fieldHeight); // Set maximum size for the button
        registerButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Create the TextArea for Registration Status
        TextArea registrationStatus = new TextArea();
        registrationStatus.setEditable(false);
        registrationStatus.setWrapText(true);
        registrationStatus.setPrefSize(fieldWidth, 60); // Set preferred size for status area
        registrationStatus.setMinSize(fieldWidth, 60); // Set minimum size for status area
        registrationStatus.setMaxSize(fieldWidth, 60); // Set maximum size for status area

        // Button action for registration
        registerButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String farmLocation = locationComboBox.getValue();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (name.isEmpty() || farmLocation == null || username.isEmpty() || password.isEmpty()) {
                registrationStatus.setText("Please fill in all fields to register.");
            } else {
                if (farmerManager.registerFarmer(name, farmLocation, username, password)) {
                    registrationStatus.setText("Registration successful. Please log in.");
                    nameField.clear();
                    locationComboBox.setValue(null);
                    usernameField.clear();
                    passwordField.clear();
                    LoginForm loginForm = new LoginForm(primaryStage, farmerManager);
                    loginForm.show();
                } else {
                    registrationStatus.setText("Username already exists. Try a different one.");
                }
            }
        });

        // Add all components to the registration box
        registrationBox.getChildren().addAll(titleLabel, nameField, locationComboBox, usernameField, passwordField, registerButton, registrationStatus);

        // Set a fixed scene size
        Scene registrationScene = new Scene(registrationBox, 800, 600);
        primaryStage.setScene(registrationScene);
        primaryStage.show();
    }
}
