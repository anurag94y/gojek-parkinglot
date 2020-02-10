package dao;

import exception.DataLayerException;

import java.util.List;

public interface ParkingLotManager {

    boolean createParkingLot(int slots) throws DataLayerException;

    String park(String regNumber, String colour) throws DataLayerException;

    boolean leave(int slotId) throws DataLayerException;

    List<String> status() throws DataLayerException;

    List<String> registrationNumbersForCarsWithColour(String colour) throws DataLayerException;

    List<Integer> slotNumbersForCarsWithColour(String colour) throws DataLayerException;

    Integer slotNumberForRegistrationNumber(String regNumber) throws DataLayerException;
}
