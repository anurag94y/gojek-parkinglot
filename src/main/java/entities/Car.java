package entities;

public class Car {
    private String registrationNumber;
    private String colour;
    private VechileType vechileType;

    public Car(String registrationNumber, String colour) {
        this.registrationNumber = registrationNumber;
        this.colour = colour;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setVechileType(VechileType vechileType) {
        this.vechileType = vechileType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColour() {
        return colour;
    }

    public VechileType getVechileType() {
        return vechileType;
    }
}
