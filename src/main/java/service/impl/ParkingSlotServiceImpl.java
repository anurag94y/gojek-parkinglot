package service.impl;

import dao.ParkingLotManager;
import dao.impl.ParkingLotManagerImpl;
import exception.ServiceLayerException;
import service.ParkingLotService;
import validator.InputValidation;

public class ParkingSlotServiceImpl implements ParkingLotService {

    private InputValidation validator = new InputValidation();
    private final ParkingLotManager parkingLotManager = new ParkingLotManagerImpl();

    @Override
    public void createParkingLot(String input) throws ServiceLayerException {
        try {
            if (validator.validateCreateParkingLotInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.createParkingLot(Integer.parseInt(splitInput[1]));
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void park(String input) throws ServiceLayerException {
        try {
            if (validator.validateParkInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.park(splitInput[1], splitInput[2]);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void leave(String input) throws ServiceLayerException {
        try {
            if (validator.validateLeaveInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.leave(Integer.parseInt(splitInput[1]));
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void status() throws ServiceLayerException {
        try {
            parkingLotManager.status();
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void registrationNumbersForCarsWithColour(String input) throws ServiceLayerException {
        try {
            if (validator.validateRegistrationNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.registrationNumbersForCarsWithColour(splitInput[1]);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void slotNumbersForCarsWithColour(String input) throws ServiceLayerException {
        try {
            if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.slotNumbersForCarsWithColour(splitInput[1]);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }

    @Override
    public void slotNumberForRegistrationNumber(String input) throws ServiceLayerException {
        try {
            if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
                String[] splitInput = input.split(" ");
                parkingLotManager.slotNumberForRegistrationNumber(splitInput[1]);
            } else {
                throw new IllegalArgumentException("Invalid Input");
            }
        } catch (Exception e) {
            throw new ServiceLayerException(e);
        }
    }
}
