import java.util.ArrayList;
import java.util.List;

public class InsurancePolicyManager {
    private List<InsurancePolicy> policies;

    public InsurancePolicyManager() {
        policies = new ArrayList<>();
        loadPolicies(); // Load policies from a database or static data
    }

    private void loadPolicies() {
        // For simplicity, adding some static policies
        policies.add(new InsurancePolicy("Basic Coverage", 100.0, "Dairy, Beef", 1, 10));
        policies.add(new InsurancePolicy("Premium Coverage", 150.0, "Dairy", 1, 8));
        // Add more policies as needed
    }

    public List<InsurancePolicy> getPolicies() {
        return policies;
    }

    public double calculatePremium(Cattle cattle) {
        // Check policy eligibility
        for (InsurancePolicy policy : policies) {
            if (cattle.getAge() >= policy.getMinAge() && cattle.getAge() <= policy.getMaxAge()
                    && policy.getCoveredBreeds().contains(cattle.getBreed())) {
                // Base premium calculation can be more complex based on health conditions, etc.
                return policy.getBasePremium();
            }
        }
        return 0.0; // No eligible policy found
    }
}
