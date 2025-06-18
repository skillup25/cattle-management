public class InsurancePolicy {
    protected String policyName;
    protected double premiumAmount;

    public InsurancePolicy(String policyName) {
        this.policyName = policyName;
        this.premiumAmount = 0.0;
    }

    public String getPolicyName() {
        return policyName;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void displayPolicyInfo() {
        System.out.println("Policy Name: " + policyName);
        System.out.println("Premium Amount: $" + premiumAmount);
    }
}

class CattlePolicy extends InsurancePolicy {
    private String breed;
    private int age;

    public CattlePolicy(String policyName, String breed, int age) {
        super(policyName);
        this.breed = breed;
        this.age = age;
    }

    public void calculatePremium() {
        if (age < 5) {
            premiumAmount = breed.equalsIgnoreCase("Premium") ? 1000 : 800;
        } else if (age >= 5 && age < 10) {
            premiumAmount = breed.equalsIgnoreCase("Premium") ? 800 : 600;
        } else {
            premiumAmount = breed.equalsIgnoreCase("Premium") ? 600 : 400;
        }
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }
}
