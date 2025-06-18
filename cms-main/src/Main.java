import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private FarmerManager farmerManager = new FarmerManager(); // Assuming FarmerManager class exists

    @Override
    public void start(Stage primaryStage) {
        // Initialize and display the WelcomePage
        WelcomePage welcomePage = new WelcomePage(primaryStage, farmerManager);
        welcomePage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
