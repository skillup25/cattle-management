import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CattleRegistrationForm {
    private Stage primaryStage;
    private FarmerManager farmerManager; // Assuming this manages farmers and their cattle

    public CattleRegistrationForm(Stage primaryStage, FarmerManager farmerManager) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
    }

    public void show() {
        VBox cattleBox = new VBox(20);
        cattleBox.setAlignment(Pos.CENTER);
        cattleBox.setPadding(new Insets(30));
        cattleBox.setStyle("-fx-background-color: #260101;");

        // Define field dimensions
        double fieldWidth = 350; // Desired width for the fields
        double fieldHeight = 50; // Preferred height
        double buttonWidth = 150; // Preferred button width

        Label titleLabel = new Label("Register Cattle");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        // Input fields for cattle details
        TextField breedField = createTextField("Breed", fieldWidth, fieldHeight);
        TextField ageField = createTextField("Age (years)", fieldWidth, fieldHeight);
        TextField weightField = createTextField("Weight (kg)", fieldWidth, fieldHeight);

        // Update the vaccination records field dimensions
        TextArea vaccinationRecordsField = new TextArea();
        vaccinationRecordsField.setPromptText("Vaccination Records");
        vaccinationRecordsField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        vaccinationRecordsField.setPrefWidth(fieldWidth);
        vaccinationRecordsField.setPrefHeight(100); // Set height specifically for TextArea
        vaccinationRecordsField.setMaxWidth(fieldWidth); // Ensure it does not exceed 350 pixels

        TextField insurancePolicyField = createTextField("Insurance Policy", fieldWidth, fieldHeight);

        // Register Button
        Button registerButton = new Button("Register Cattle");
        registerButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");
        registerButton.setPrefWidth(buttonWidth); // Set button width
        registerButton.setOnAction(e -> {
            String breed = breedField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim());
            String vaccinationRecords = vaccinationRecordsField.getText().trim();
            String insurancePolicy = insurancePolicyField.getText().trim();

            Cattle newCattle = new Cattle(breed, age, weight, vaccinationRecords, insurancePolicy);
            farmerManager.registerCattle(newCattle); // Assuming FarmerManager has a method to register cattle

            // Display success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Cattle registered successfully with ID: " + newCattle.getId());
            alert.showAndWait();

            // Clear the fields after registration
            breedField.clear();
            ageField.clear();
            weightField.clear();
            vaccinationRecordsField.clear();
            insurancePolicyField.clear();
        });

        // Add all components to the cattle box
        cattleBox.getChildren().addAll(titleLabel, breedField, ageField, weightField, vaccinationRecordsField, insurancePolicyField, registerButton);

        // Set scene size and show
        Scene cattleScene = new Scene(cattleBox, 800, 600);
        primaryStage.setScene(cattleScene);
        primaryStage.setTitle("Cattle Registration");
        primaryStage.show();
    }

    private TextField createTextField(String promptText, double width, double height) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        textField.setPrefWidth(width);
        textField.setMinWidth(width); // Minimum width
        textField.setMaxWidth(width); // Maximum width
        textField.setPrefHeight(height);
        return textField;
    }
}
