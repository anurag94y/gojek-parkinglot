import constants.ParkingFunctionNameConstants;
import service.ParkingLotService;
import service.impl.ParkingSlotServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {

    private static final ParkingLotService parkingLotService = new ParkingSlotServiceImpl();

    public static void main(String[] args) {
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equals(ParkingFunctionNameConstants.EXIT)) {
                    System.out.println("Parking Slot is getting closed :P ");
                    return ;
                }
                executeInput(input);
            }
        } else {
            readFromFile(args[0]);
        }
    }

    public static void readFromFile(String path) {
        List<String> lines = readFileInList(path);
        for (String line : lines) {
            executeInput(line);
        }
    }

    private static void executeInput(String line) {
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
            e.printStackTrace();
            System.out.println(e.getMessage());
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
