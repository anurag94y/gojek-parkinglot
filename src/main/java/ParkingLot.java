import constants.ParkingFunctionNameConstants;
import entities.Car;
import entities.Slot;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;
import service.ParkingLotService;
import service.impl.ParkingSlotServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParkingLot {

    private final ParkingLotService parkingLotService = new ParkingSlotServiceImpl();

    @Test
    public void main() {
        List<String> lines = readFileInList("/Users/anurag.y/Documents/media/ParkingLot/src/main/resources/input.txt");
        for (String line : lines) {
            try {
                String[] input = line.split(" ");
                if (input.length == 0) {
                    System.out.println("Invalid Input");
                }
                switch (input[0]) {
                    case ParkingFunctionNameConstants.CREATE_PARKING_LOT:
                        parkingLotService.createParkingLot(line);
                        break;
                    case ParkingFunctionNameConstants.PARK:
                        parkingLotService.park(line);
                        break;
                    case ParkingFunctionNameConstants.LEAVE:
                        parkingLotService.leave(line);
                        break;
                    case ParkingFunctionNameConstants.STATUS:
                        parkingLotService.status();
                        break;
                    case ParkingFunctionNameConstants.REG_NUMBER_FOR_CARS_WITH_COLOR:
                        parkingLotService.registrationNumbersForCarsWithColour(line);
                        break;
                    case ParkingFunctionNameConstants.SLOTS_NUMBER_FOR_CARS_WITH_COLOR:
                        parkingLotService.slotNumbersForCarsWithColour(line);
                        break;
                    case ParkingFunctionNameConstants.SLOTS_NUMBER_FOR_REG_NUMBER:
                        parkingLotService.slotNumberForRegistrationNumber(line);
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            } catch (Exception e) {

            }
        }
    }

    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
