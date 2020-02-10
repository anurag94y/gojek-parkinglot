package service.impl;

import dao.ParkingLotManager;
import dao.impl.ParkingLotManagerImpl;
import service.ParkingLotService;
import validator.InputValidation;

public class ParkingSlotServiceImpl implements ParkingLotService {

    private InputValidation validator = new InputValidation();
    private final ParkingLotManager parkingLotManager = new ParkingLotManagerImpl();

    @Override
    public void createParkingLot(String input) {
        if (validator.validateCreateParkingLotInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.createParkingLot(Integer.parseInt(splitInput[1]));
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    @Override
    public void park(String input) {
        if (validator.validateParkInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.park(splitInput[1], splitInput[2]);
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    @Override
    public void leave(String input) {
        if (validator.validateLeaveInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.leave(Integer.parseInt(splitInput[1]));
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    @Override
    public void status() {

    }

    @Override
    public void registrationNumbersForCarsWithColour(String input) {
        if (validator.validateRegistrationNumbersForCarsWithColourInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.registrationNumbersForCarsWithColour(splitInput[1]);
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    @Override
    public void slotNumbersForCarsWithColour(String input) {
        if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.slotNumbersForCarsWithColour(splitInput[1]);
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }

    @Override
    public void slotNumberForRegistrationNumber(String input) {
        if (validator.validateSlotNumbersForCarsWithColourInput(input)) {
            String[] splitInput = input.split(" ");
            parkingLotManager.slotNumberForRegistrationNumber(splitInput[1]);
        }
        else {
            throw new IllegalArgumentException("Invalid Input");
        }
    }
}
