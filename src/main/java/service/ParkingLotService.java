package service;

import exception.ServiceLayerException;

public interface ParkingLotService {
    void createParkingLot(String input) throws ServiceLayerException;

    void park(String input) throws ServiceLayerException;

    void leave(String input) throws ServiceLayerException;

    void status() throws ServiceLayerException;

    void registrationNumbersForCarsWithColour(String input) throws ServiceLayerException;

    void slotNumbersForCarsWithColour(String input) throws ServiceLayerException;

    void slotNumberForRegistrationNumber(String input) throws ServiceLayerException;
}
