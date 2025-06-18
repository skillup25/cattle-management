import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ClaimSubmissionFX extends Application {
    private List<InsuranceClaim> claims = new ArrayList<>(); // List to store claims

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Submit Insurance Claim");

        // Setting up the layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #260101;"); // Set background color

        // Input fields for claim details
        Text cattleIdLabel = new Text("Cattle ID:");
        cattleIdLabel.setFont(Font.font(16)); // Set larger font size
        cattleIdLabel.setFill(Color.WHITE); // Set text color to white
        TextField cattleIdField = new TextField();
        cattleIdField.setPrefWidth(350); // Set preferred width
        cattleIdField.setPrefHeight(50); // Set preferred height
        grid.add(cattleIdLabel, 0, 0);
        grid.add(cattleIdField, 1, 0);

        Text descriptionLabel = new Text("Claim Description:");
        descriptionLabel.setFont(Font.font(16));
        descriptionLabel.setFill(Color.WHITE);
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefWidth(350);
        descriptionArea.setPrefHeight(100);
        grid.add(descriptionLabel, 0, 1);
        grid.add(descriptionArea, 1, 1);

        Button submitButton = new Button("Submit Claim");
        submitButton.setPrefWidth(150); // Set button width
        grid.add(submitButton, 0, 2);

        Text resultText = new Text();
        resultText.setFont(Font.font(16));
        resultText.setFill(Color.WHITE);
        grid.add(resultText, 1, 3);

        // Define action for submit button
        submitButton.setOnAction(e -> {
            String cattleId = cattleIdField.getText();
            String description = descriptionArea.getText();

            if (cattleId.isEmpty() || description.isEmpty()) {
                resultText.setText("Please fill all fields.");
                return;
            }

            InsuranceClaim claim = new InsuranceClaim(cattleId, description);
            claims.add(claim); // Add claim to the list
            resultText.setText("Claim submitted successfully! Claim ID: " + claim.getClaimId());
        });

        Scene scene = new Scene(grid, 800, 600); // Set the window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
