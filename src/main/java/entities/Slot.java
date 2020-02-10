package entities;

public class Slot {
    private int id;
    private boolean isOccupied;
    private Car carDetails;

    public Slot(int id, boolean isOccupied, Car carDetails) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.carDetails = carDetails;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setCarDetails(Car carDetails) {
        this.carDetails = carDetails;
    }

    public int getId() {
        return id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Car getCarDetails() {
        return carDetails;
    }
}
