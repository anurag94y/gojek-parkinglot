package service;

public interface ParkingLotService {
    void createParkingLot(String input);

    void park(String input);

    void leave(String input);

    void status();

    void registrationNumbersForCarsWithColour(String input);

    void slotNumbersForCarsWithColour(String input);

    void slotNumberForRegistrationNumber(String input);
}
