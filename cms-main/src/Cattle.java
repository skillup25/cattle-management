import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Cattle {
    private String id;
    private String breed;
    private int age; 
    private double weight; // in kg
    private String vaccinationRecords;
    private String insurancePolicy;

    public Cattle(String breed, int age, double weight, String vaccinationRecords, String insurancePolicy) {
        this.id = generateUniqueId();
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.vaccinationRecords = vaccinationRecords;
        this.insurancePolicy = insurancePolicy;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    // Other getters and setters...

    public String generateChecksum() {
        String data = breed + age + weight + vaccinationRecords + insurancePolicy;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes());
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
