public class CattlePolicy extends InsurancePolicy {
    private String breed;
    private int age;

    public CattlePolicy(String policyName, double premiumAmount, String coveredBreeds, int minAge, int maxAge, String breed, int age) {
        super(policyName, premiumAmount, coveredBreeds, minAge, maxAge);
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
