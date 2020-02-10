package validator;

public class InputValidation {

    public boolean validateCreateParkingLotInput(String input){
        return validateInputWithSlotId(input);
    }

    public boolean validateParkInput(String input){
        return validateInputWithFixSize(input, 3);
    }

    public boolean validateLeaveInput(String input){
        return validateInputWithSlotId(input);
    }
    

    public boolean validateRegistrationNumbersForCarsWithColourInput(String input){
        return validateInputWithFixSize(input, 2);
    }

    public boolean validateSlotNumbersForCarsWithColourInput(String input){
        return validateInputWithFixSize(input, 2);
    }

    public boolean validateSlotNumberForRegistrationNumberInput(String input){
        return validateInputWithFixSize(input, 2);
    }

    private boolean validateInputWithSlotId(String input){
        String[] splitInput = input.split(" ");
        if (splitInput.length == 2) {
            try {
                int slotId = Integer.parseInt(splitInput[1]);
                if (slotId > 0)
                    return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
    
    private boolean validateInputWithFixSize(String input, int size) {
        String[] splitInput = input.split(" ");
        if (splitInput.length == size) {
            return true;
        }
        return false;
    }
}
