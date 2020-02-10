package dao;

import entities.Car;
import entities.Slot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public interface ParkingLotManager {

    void createParkingLot(int slots);

    void park(String regNumber, String colour);

    void leave(int slotId);

    void status();

    void registrationNumbersForCarsWithColour(String colour);

    void slotNumbersForCarsWithColour(String colour);

    void slotNumberForRegistrationNumber(String regNumber);
}
