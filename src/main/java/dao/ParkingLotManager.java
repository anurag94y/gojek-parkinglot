package dao;

import entities.Car;
import entities.Slot;
import exception.DataLayerException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public interface ParkingLotManager {

    void createParkingLot(int slots) throws DataLayerException;

    void park(String regNumber, String colour) throws DataLayerException;

    void leave(int slotId) throws DataLayerException;

    void status() throws DataLayerException;

    void registrationNumbersForCarsWithColour(String colour) throws DataLayerException;

    void slotNumbersForCarsWithColour(String colour) throws DataLayerException;

    void slotNumberForRegistrationNumber(String regNumber) throws DataLayerException;
}
