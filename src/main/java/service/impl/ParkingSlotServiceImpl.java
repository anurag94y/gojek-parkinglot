package service.impl;

import dao.ParkingLotManager;
import dao.impl.ParkingLotManagerImpl;
import exception.DataLayerException;
import exception.ServiceLayerException;
import service.ParkingLotService;
import validator.InputValidation;

import java.util.List;
import java.util.StringJoiner;

public class ParkingSlotServiceImpl implements ParkingLotService {

    private InputValidation validator = new InputValidation();
    private final ParkingLotManager parkingLotManager = new ParkingLotManagerImpl();

    @Override
    public void createParkingLot(String input) throws ServiceLayerException {
        try {
            if (validator.validateCreateParkingLotInput(input)) {
                String[] splitInput = input.split(" ");
                if (parkingLotManager.createParkingLot(Integer.parseInt(splitInput[1]))) {
                    System.out.println("Created a parking lot with " + splitInput[1] + " slots");
                } else {
                    throw new ServiceLayerException("Failed to create parking lot");
                }
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void park(String input) throws ServiceLayerException {
        try {
            if (validator.validateParkInput(input)) {
                String[] splitInput = input.split(" ");
                String response = parkingLotManager.park(splitInput[1], splitInput[2]);
                System.out.println(response);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void leave(String input) throws ServiceLayerException {
        try {
            if (validator.validateLeaveInput(input)) {
                String[] splitInput = input.split(" ");
                if (parkingLotManager.leave(Integer.parseInt(splitInput[1]))) {
                    System.out.println("Slot number " + splitInput[1] + " is free");
                } else {
                    throw new ServiceLayerException("Failed to leave the slot");
                }
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void status() throws ServiceLayerException {
        try {
            List<String> slotStatus = parkingLotManager.status();
            if (slotStatus.size() == 0)
                System.out.println("Sorry, parking lot is empty.");
            else {
                System.out.println("Slot No.\tRegistration No\tColour");
                slotStatus.forEach(t -> System.out.println(t));
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void registrationNumbersForCarsWithColour(String input) throws ServiceLayerException {
        try {
            if (validator.validateRegistrationNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                List<String> regNumbers = parkingLotManager.registrationNumbersForCarsWithColour(splitInput[1]);
                if (regNumbers.size() == 0)
                    System.out.println("Not Found");
                else
                    System.out.println(String.join(",", regNumbers));
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void slotNumbersForCarsWithColour(String input) throws ServiceLayerException {
        try {
            if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                List<Integer> slotIds = parkingLotManager.slotNumbersForCarsWithColour(splitInput[1]);
                if (slotIds.size() == 0)
                    System.out.println("Not Found");
                else {
                    StringJoiner stringJoiner = new StringJoiner(",");
                    for (Integer slot : slotIds) {
                        stringJoiner.add(slot + "");
                    }
                    System.out.println(stringJoiner.toString());
                }
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void slotNumberForRegistrationNumber(String input) throws ServiceLayerException {
        try {
            if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                int slotId = parkingLotManager.slotNumberForRegistrationNumber(splitInput[1]);
                if (slotId == 0)
                    System.out.println("Not Found");
                else
                    System.out.println(slotId);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (DataLayerException e) {
            throw new ServiceLayerException(e);
        }
    }
}
