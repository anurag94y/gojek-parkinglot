package validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InputValidationTest {

    private InputValidation inputValidation;

    @Before
    public void setUp() throws Exception {
        inputValidation = new InputValidation();
    }

    @Test
    public void validateCreateParkingLotInput() {
        String input = "create_parking_lot 3";
        assertTrue(inputValidation.validateCreateParkingLotInput(input));
    }

    @Test
    public void validateCreateParkingLotInputWithIllegalArgument() {
        String input = "create_parking_lot invalid_slot_id";
        assertFalse(inputValidation.validateCreateParkingLotInput(input));
    }

    @Test
    public void validateCreateParkingLotInputWithNegativeSlotId() {
        String input = "create_parking_lot -1";
        assertFalse(inputValidation.validateCreateParkingLotInput(input));
    }

    @Test
    public void validateCreateParkingLotInputWithLessInputArgs() {
        String input = "create_parking_lot";
        assertFalse(inputValidation.validateCreateParkingLotInput(input));
    }

    @Test
    public void validateParkInput() {
        String input = "park RegNumber Colour";
        assertTrue(inputValidation.validateParkInput(input));
    }

    @Test
    public void validateParkInputWithLessArgs() {
        String input = "park RegNumber";
        assertFalse(inputValidation.validateParkInput(input));
    }

    @Test
    public void validateLeaveInput() {
        String input = "leave 4";
        assertTrue(inputValidation.validateLeaveInput(input));
    }

    @Test
    public void validateLeaveInputWithIllegalArgs() {
        String input = "leave invalid_slot_id";
        assertFalse(inputValidation.validateLeaveInput(input));
    }

    @Test
    public void validateLeaveInputWithNegativeSlotId() {
        String input = "leave -1";
        assertFalse(inputValidation.validateLeaveInput(input));
    }

    @Test
    public void validateLeaveInputWithLessArgs() {
        String input = "leave";
        assertFalse(inputValidation.validateLeaveInput(input));
    }

    @Test
    public void validateRegistrationNumbersForCarsWithColourInput() {
        String input = "registration_numbers_for_cars_with_colour White";
        assertTrue(inputValidation.validateRegistrationNumbersForCarsWithColourInput(input));
    }

    @Test
    public void validateRegistrationNumbersForCarsWithColourInputWithLessArgs() {
        String input = "registration_numbers_for_cars_with_colour";
        assertFalse(inputValidation.validateRegistrationNumbersForCarsWithColourInput(input));
    }

    @Test
    public void validateSlotNumbersForCarsWithColourInput() {
        String input = "slot_numbers_for_cars_with_colour White";
        assertTrue(inputValidation.validateSlotNumbersForCarsWithColourInput(input));
    }

    @Test
    public void validateSlotNumbersForCarsWithColourInputLessArgs() {
        String input = "slot_numbers_for_cars_with_colour";
        assertFalse(inputValidation.validateSlotNumbersForCarsWithColourInput(input));
    }

    @Test
    public void validateSlotNumberForRegistrationNumberInput() {
        String input = "slot_number_for_registration_number RegNumber";
        assertTrue(inputValidation.validateSlotNumberForRegistrationNumberInput(input));
    }

    @Test
    public void validateSlotNumberForRegistrationNumberInputLessArgs() {
        String input = "slot_number_for_registration_number";
        assertFalse(inputValidation.validateSlotNumberForRegistrationNumberInput(input));
    }
}