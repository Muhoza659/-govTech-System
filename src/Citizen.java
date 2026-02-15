public class Citizen {
    private String nationalId;
    private String name;

    public Citizen(String nationalId, String name) {
        this.nationalId = nationalId;
        this.name = name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (ID: " + nationalId + ")";
    }
}
