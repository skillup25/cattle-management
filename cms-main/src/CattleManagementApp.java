import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CattleManagementApp extends Application {

    private List<Cattle> cattleList = new ArrayList<>();
    private TableView<Cattle> cattleTable;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cattle Management System");

        // Create a grid layout for the form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels and Input Fields for Cattle Data
        Label breedLabel = new Label("Breed:");
        TextField breedInput = new TextField();
        Label ageLabel = new Label("Age:");
        TextField ageInput = new TextField();
        Label weightLabel = new Label("Weight:");
        TextField weightInput = new TextField();
        Label vaccinationLabel = new Label("Vaccination Records:");
        TextField vaccinationInput = new TextField();

        // Button to save cattle data
        Button saveButton = new Button("Save Cattle");
        saveButton.setOnAction(e -> {
            String breed = breedInput.getText();
            int age = Integer.parseInt(ageInput.getText());
            double weight = Double.parseDouble(weightInput.getText());
            String vaccinationRecords = vaccinationInput.getText();

            Cattle cattle = new Cattle(breed, age, weight, vaccinationRecords);
            cattleList.add(cattle);
            updateCattleTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cattle data saved with checksum: " + cattle.getChecksum());
            alert.showAndWait();

            AuditLogger.log("Cattle saved with UID: " + cattle.getChecksum());
        });

        // Button to display cattle
        Button displayButton = new Button("Display Cattle");
        displayButton.setOnAction(e -> updateCattleTable());

        // Set up cattle table
        cattleTable = new TableView<>();
        TableColumn<Cattle, String> breedColumn = new TableColumn<>("Breed");
        breedColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBreed()));
        
        TableColumn<Cattle, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        
        TableColumn<Cattle, Double> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWeight()).asObject());
        
        TableColumn<Cattle, String> vaccinationColumn = new TableColumn<>("Vaccination Records");
        vaccinationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVaccinationRecords()));
        
        cattleTable.getColumns().addAll(breedColumn, ageColumn, weightColumn, vaccinationColumn);

        // Add UI elements to the grid layout
        grid.add(breedLabel, 0, 0);
        grid.add(breedInput, 1, 0);
        grid.add(ageLabel, 0, 1);
        grid.add(ageInput, 1, 1);
        grid.add(weightLabel, 0, 2);
        grid.add(weightInput, 1, 2);
        grid.add(vaccinationLabel, 0, 3);
        grid.add(vaccinationInput, 1, 3);
        grid.add(saveButton, 0, 4);
        grid.add(displayButton, 1, 4);
        
        VBox vbox = new VBox(grid, cattleTable);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateCattleTable() {
        cattleTable.getItems().clear();
        cattleTable.getItems().addAll(cattleList);
    }

    // Cattle Class
    class Cattle {
        private String breed;
        private int age;
        private double weight;
        private String vaccinationRecords;
        private String checksum;

        public Cattle(String breed, int age, double weight, String vaccinationRecords) {
            this.breed = breed;
            this.age = age;
            this.weight = weight;
            this.vaccinationRecords = vaccinationRecords;
            this.checksum = ChecksumUtil.generateChecksum(this);
        }

        public String getBreed() { return breed; }
        public int getAge() { return age; }
        public double getWeight() { return weight; }
        public String getVaccinationRecords() { return vaccinationRecords; }
        public String getChecksum() { return checksum; }

        public boolean verifyChecksum() {
            return Objects.equals(checksum, ChecksumUtil.generateChecksum(this));
        }
    }

    // Checksum Utility Class
    static class ChecksumUtil {
        public static String generateChecksum(Cattle cattle) {
            String data = cattle.getBreed() + cattle.getAge() + cattle.getWeight() + cattle.getVaccinationRecords();
            return hashSHA256(data);
        }

        private static String hashSHA256(String data) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes());
                StringBuilder hexString = new StringBuilder();
                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Audit Logger Class
    static class AuditLogger {
        private static final String LOG_FILE_PATH = "audit_log.txt";

        public static void log(String message) {
            try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true);
                 PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.println(LocalDateTime.now() + " - " + message);
            } catch (IOException e) {
                System.err.println("Error writing to audit log: " + e.getMessage());
            }
        }
    }

    // Main Method to Launch Application
    public static void main(String[] args) {
        launch(args);
    }
}
