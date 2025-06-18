import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CattleOwnershipView {
    private Stage primaryStage;
    private FarmerManager farmerManager; // Assume this manager can provide cattle data

    public CattleOwnershipView(Stage primaryStage, FarmerManager farmerManager) {
        this.primaryStage = primaryStage;
        this.farmerManager = farmerManager;
    }

    public void show() {
        // Create a VBox layout for the scene
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Create a TableView for displaying cattle information
        TableView<Cattle> cattleTable = new TableView<>();

        // Define the columns for the table
        TableColumn<Cattle, String> breedColumn = new TableColumn<>("Breed");
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));

        TableColumn<Cattle, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Cattle, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<Cattle, String> vaccinationColumn = new TableColumn<>("Vaccination Records");
        vaccinationColumn.setCellValueFactory(new PropertyValueFactory<>("vaccinationRecords"));

        // Add columns to the table
        cattleTable.getColumns().addAll(breedColumn, ageColumn, weightColumn, vaccinationColumn);

        // Fetch the cattle data for the logged-in farmer
        ObservableList<Cattle> cattleData = FXCollections.observableArrayList(farmerManager.getCattleByFarmer("someFarmerId")); // Replace with actual farmer ID
        cattleTable.setItems(cattleData); // Set data into the table

        // Add the table to the VBox layout
        vbox.getChildren().addAll(cattleTable);

        // Create a scene and set it on the primary stage
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cattle Ownership");
        primaryStage.show();
    }
}
