import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Dashboard {
    private Stage primaryStage;
    private FarmerManager farmerManager; // Manager that handles farmers' data
    private String username; // Username of the logged-in farmer

    public Dashboard(Stage primaryStage, FarmerManager farmerManager, String username) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
        this.username = username;
    }

    public void show() {
        VBox dashboardBox = new VBox(20);
        dashboardBox.setAlignment(Pos.CENTER);
        dashboardBox.setPadding(new Insets(30));
        dashboardBox.setStyle("-fx-background-color: #260101;");

        // Title Label
        Label titleLabel = new Label("Welcome to the Farmer Dashboard, " + username + "!");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        // Button for Cattle Registration
        Button registerCattleButton = new Button("Register Cattle");
        registerCattleButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");

        // Action for Cattle Registration button
        registerCattleButton.setOnAction(e -> {
            System.out.println("Register Cattle button clicked");
            CattleRegistrationForm cattleRegistrationForm = new CattleRegistrationForm(primaryStage, farmerManager);
            cattleRegistrationForm.show();
        });

        // Button for Insurance Policy Management
        Button manageInsuranceButton = new Button("Manage Insurance Policies");
        manageInsuranceButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");

        // Action for Insurance Policy Management button
        manageInsuranceButton.setOnAction(e -> {
            System.out.println("Manage Insurance Policies button clicked");
            InsuranceManagerFX insuranceManager = new InsuranceManagerFX();
            insuranceManager.start(primaryStage); // Show the insurance manager interface
        });

        // Button for Claim Submission
        Button submitClaimButton = new Button("Submit Insurance Claim");
        submitClaimButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");

        // Action for Claim Submission button
        submitClaimButton.setOnAction(e -> {
            System.out.println("Submit Insurance Claim button clicked");
            ClaimSubmissionForm claimSubmissionForm = new ClaimSubmissionForm(primaryStage, farmerManager);
            claimSubmissionForm.show();
        });

        // New button for Viewing Cattle Ownership
        Button viewOwnershipButton = new Button("View Cattle Ownership");
        viewOwnershipButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");

        // Action for View Cattle Ownership button
        viewOwnershipButton.setOnAction(e -> {
            System.out.println("View Cattle Ownership button clicked");
            CattleOwnershipView cattleOwnershipView = new CattleOwnershipView(primaryStage, farmerManager);
            cattleOwnershipView.show(); // Assuming this class is defined to handle cattle ownership display
        });

        // Add all components to the dashboard box
        dashboardBox.getChildren().addAll(titleLabel, registerCattleButton, manageInsuranceButton, submitClaimButton, viewOwnershipButton);

        // Set scene size and show
        Scene dashboardScene = new Scene(dashboardBox, 800, 600);
        primaryStage.setScene(dashboardScene);
        primaryStage.setTitle("Farmer Dashboard");
        primaryStage.show();
    }
}
