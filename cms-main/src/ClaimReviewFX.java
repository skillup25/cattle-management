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

public class ClaimReviewFX extends Application {
    private List<InsuranceClaim> claims; // List of claims to review

    public ClaimReviewFX(List<InsuranceClaim> claims) {
        this.claims = claims; // Inject the list of claims
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Review Insurance Claims");

        // Setting up the layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #260101;"); // Set background color

        Text claimListLabel = new Text("Claims to Review:");
        claimListLabel.setFont(Font.font(16));
        claimListLabel.setFill(Color.WHITE);
        grid.add(claimListLabel, 0, 0);

        ListView<InsuranceClaim> claimListView = new ListView<>();
        claimListView.getItems().addAll(claims); // Populate the list view with claims
        grid.add(claimListView, 0, 1, 2, 1); // Span 2 columns

        Button approveButton = new Button("Approve Claim");
        Button rejectButton = new Button("Reject Claim");
        grid.add(approveButton, 0, 2);
        grid.add(rejectButton, 1, 2);

        Text resultText = new Text();
        resultText.setFont(Font.font(16));
        resultText.setFill(Color.WHITE);
        grid.add(resultText, 0, 3, 2, 1); // Span 2 columns

        // Define actions for buttons
        approveButton.setOnAction(e -> {
            InsuranceClaim selectedClaim = claimListView.getSelectionModel().getSelectedItem();
            if (selectedClaim != null) {
                selectedClaim.setStatus("Approved");
                resultText.setText("Claim approved: " + selectedClaim.getClaimId());
            } else {
                resultText.setText("Please select a claim to approve.");
            }
        });

        rejectButton.setOnAction(e -> {
            InsuranceClaim selectedClaim = claimListView.getSelectionModel().getSelectedItem();
            if (selectedClaim != null) {
                selectedClaim.setStatus("Rejected");
                resultText.setText("Claim rejected: " + selectedClaim.getClaimId());
            } else {
                resultText.setText("Please select a claim to reject.");
            }
        });

        Scene scene = new Scene(grid, 800, 600); // Set the window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
