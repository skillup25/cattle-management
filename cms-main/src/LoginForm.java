import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginForm {
    private Stage primaryStage;
    private FarmerManager farmerManager;

    public LoginForm(Stage primaryStage, FarmerManager farmerManager) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
    }

    public void show() {
        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setPadding(new Insets(30));
        loginBox.setStyle("-fx-background-color: #260101;");

        Label titleLabel = new Label("Login to Your Account");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setTextFill(Color.WHITE);

        // Set fixed width and height for all fields and buttons
        double fieldWidth = 350; // Preferred width matching the RegistrationForm
        double fieldHeight = 50; // Preferred height
        double buttonWidth = 150; // Preferred width for the buttons

        // Username TextField with matching style and width
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        usernameField.setPrefSize(fieldWidth, fieldHeight); 
        usernameField.setMinSize(fieldWidth, fieldHeight);
        usernameField.setMaxSize(fieldWidth, fieldHeight);

        // Password PasswordField with matching style and width
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");
        passwordField.setPrefSize(fieldWidth, fieldHeight); 
        passwordField.setMinSize(fieldWidth, fieldHeight);
        passwordField.setMaxSize(fieldWidth, fieldHeight);

        // Login Button with matching style
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(buttonWidth, fieldHeight);
        loginButton.setMinSize(buttonWidth, fieldHeight);
        loginButton.setMaxSize(buttonWidth, fieldHeight);
        loginButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: black;");

        // Register Button (initially hidden), matching RegistrationForm button style
        Button registerButton = new Button("Register");
        registerButton.setPrefSize(buttonWidth, fieldHeight);
        registerButton.setMinSize(buttonWidth, fieldHeight);
        registerButton.setMaxSize(buttonWidth, fieldHeight);
        registerButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #F5F5DC; -fx-text-fill: maroon;");
        registerButton.setVisible(false);

        // Registration form redirection action
        registerButton.setOnAction(e -> {
            RegistrationForm registrationForm = new RegistrationForm(primaryStage, farmerManager);
            registrationForm.show();
        });

        // Login status TextArea with matching style and width
        TextArea loginStatus = new TextArea();
        loginStatus.setEditable(false);
        loginStatus.setWrapText(true);
        loginStatus.setPrefSize(fieldWidth, 60);
        loginStatus.setMinSize(fieldWidth, 60);
        loginStatus.setMaxSize(fieldWidth, 60);
        loginStatus.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;");

        // Login button action with condition for showing registration button
        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                loginStatus.setText("Please fill in both fields to login.");
                registerButton.setVisible(false);
            } else {
                // Existing logic added here
                if (farmerManager.loginFarmer(username, password)) {
                    loginStatus.setText("Login successful. Welcome, " + username + "!");
                    // Show the Dashboard upon successful login
                    Dashboard dashboard = new Dashboard(primaryStage, farmerManager, username);
                    dashboard.show();
                } else {
                    loginStatus.setText("Invalid username or password. Please try again.");
                    registerButton.setVisible(true);
                }
            }
        });

        // Add all components to the login box
        loginBox.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, registerButton, loginStatus);

        // Set scene size and show
        Scene loginScene = new Scene(loginBox, 800, 600);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
