public class Farmer {
    private String name;
    private String farmLocation;
    private String username;
    private String password;

    public Farmer(String name, String farmLocation, String username, String password) {
        this.name = name;
        this.farmLocation = farmLocation;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
