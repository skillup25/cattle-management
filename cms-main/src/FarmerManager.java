import java.util.ArrayList;
import java.util.List;

public class FarmerManager {
    private List<Farmer> farmers;
    private List<Cattle> cattleList; // List to store registered cattle

    public FarmerManager() {
        farmers = new ArrayList<>();
        cattleList = new ArrayList<>(); // Initialize the cattle list
    }

    // Register a new farmer
    public boolean registerFarmer(String name, String farmLocation, String username, String password) {
        for (Farmer farmer : farmers) {
            if (farmer.getUsername().equals(username)) {
                return false; // Username already exists
            }
        }
        farmers.add(new Farmer(name, farmLocation, username, password));
        return true; // Registration successful
    }

    // Login a farmer
    public boolean loginFarmer(String username, String password) {
        for (Farmer farmer : farmers) {
            if (farmer.getUsername().equals(username) && farmer.getPassword().equals(password)) {
                return true; // Login successful
            }
        }
        return false; // Invalid login
    }

    // Register cattle
    public void registerCattle(Cattle cattle) {
        cattleList.add(cattle); // Add the cattle to the list
        System.out.println("Cattle registered: " + cattle.getId());
    }

    // You can add more methods to manage farmers and cattle as needed
}
