import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsuranceManagerFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cattle Insurance Policy");

        // Setting up the layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #260101;"); // Set background color

        // Input fields for policy details
        Text policyNameLabel = new Text("Select Policy:");
        policyNameLabel.setFont(Font.font(16)); // Set larger font size
        policyNameLabel.setFill(Color.WHITE); // Set text color to white
        ComboBox<String> policyNameComboBox = new ComboBox<>();
        policyNameComboBox.getItems().addAll("Basic Cattle Insurance", "Premium Cattle Insurance", "Comprehensive Cattle Insurance");
        grid.add(policyNameLabel, 0, 0);
        grid.add(policyNameComboBox, 1, 0);

        Text breedLabel = new Text("Cattle Breed:");
        breedLabel.setFont(Font.font(16)); // Set larger font size
        breedLabel.setFill(Color.WHITE); // Set text color to white
        TextField breedField = new TextField();
        breedField.setPrefWidth(350); // Set preferred width
        breedField.setPrefHeight(50); // Set preferred height
        grid.add(breedLabel, 0, 1);
        grid.add(breedField, 1, 1);

        Text ageLabel = new Text("Cattle Age:");
        ageLabel.setFont(Font.font(16)); // Set larger font size
        ageLabel.setFill(Color.WHITE); // Set text color to white
        TextField ageField = new TextField();
        ageField.setPrefWidth(350); // Set preferred width
        ageField.setPrefHeight(50); // Set preferred height
        grid.add(ageLabel, 0, 2);
        grid.add(ageField, 1, 2);

        Button calculateButton = new Button("Calculate Premium");
        calculateButton.setPrefWidth(150); // Set button width
        grid.add(calculateButton, 0, 3);

        Text resultText = new Text();
        resultText.setFont(Font.font(16)); // Set larger font size
        resultText.setFill(Color.WHITE); // Set text color to white
        grid.add(resultText, 1, 4);

        // Define action for calculate button
        calculateButton.setOnAction(e -> {
            String policyName = policyNameComboBox.getValue(); // Get selected policy
            if (policyName == null) {
                resultText.setText("Please select a policy.");
                return;
            }

            String breed = breedField.getText();
            int age;
            try {
                age = Integer.parseInt(ageField.getText());
            } catch (NumberFormatException ex) {
                resultText.setText("Please enter a valid age.");
                return;
            }

            CattlePolicy cattlePolicy = new CattlePolicy(policyName, breed, age);
            cattlePolicy.calculatePremium();

            resultText.setText("Policy: " + cattlePolicy.getPolicyName() + "\n" +
                    "Breed: " + cattlePolicy.getBreed() + "\n" +
                    "Age: " + cattlePolicy.getAge() + "\n" +
                    "Premium: $" + cattlePolicy.getPremiumAmount());
        });

        // Set scene size
        Scene scene = new Scene(grid, 800, 600); // Set the window size to 800x600
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

