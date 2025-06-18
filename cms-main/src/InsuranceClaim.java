import java.util.UUID;

public class InsuranceClaim {
    private String claimId;
    private String cattleId; // ID of the associated cattle
    private String description;
    private String status; // e.g., Pending, Approved, Rejected

    public InsuranceClaim(String cattleId, String description) {
        this.claimId = generateUniqueClaimId();
        this.cattleId = cattleId;
        this.description = description;
        this.status = "Pending"; // Initial status
    }

    private String generateUniqueClaimId() {
        return UUID.randomUUID().toString(); // Unique claim ID
    }

    // Getters and Setters
    public String getClaimId() {
        return claimId;
    }

    public String getCattleId() {
        return cattleId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
