package dao.impl;

import dao.ParkingLotManager;
import entities.Car;
import entities.Slot;
import exception.DataLayerException;

import javax.xml.crypto.Data;
import java.util.*;

public class ParkingLotManagerImpl implements ParkingLotManager {
    private Queue<Integer> freeSlots = new PriorityQueue<>();
    private Map<String, Set<Integer>> colourToSlotIdMapping = new HashMap<>();
    private Map<String, Integer> regNumberToSlotIdMapping = new HashMap<>();
    private Map<Integer, Slot> slotIdToSlotMapping = new HashMap<>();
    private boolean isParkingLotCreated = false;

    public void createParkingLot(int slots) throws DataLayerException {
        if (isParkingLotCreated == true) {
            throw new DataLayerException("ParkingLot Already created");
        }
        try {
            freeSlots = new PriorityQueue<>();
            colourToSlotIdMapping = new HashMap<>();
            slotIdToSlotMapping = new HashMap<>();
            regNumberToSlotIdMapping = new HashMap<>();
            for (int i = 1; i <= slots; i++) {
                freeSlots.add(i);
                slotIdToSlotMapping.put(i, new Slot(i, false, null));
            }
            System.out.println("Created a parking lot with " + slots + " slots");
            isParkingLotCreated = true;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public void park(String regNumber, String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (freeSlots.isEmpty()) {
                System.out.println("Sorry, parking lot is full");
                return;
            }
            int slotId = freeSlots.poll();
            slotIdToSlotMapping.get(slotId).setCarDetails(new Car(regNumber, colour));
            slotIdToSlotMapping.get(slotId).setOccupied(true);

            if (!colourToSlotIdMapping.containsKey(colour)) {
                colourToSlotIdMapping.put(colour, new HashSet<>());
            }
            colourToSlotIdMapping.compute(colour, (k, v) -> {
                v.add(slotId);
                return v;
            });
            regNumberToSlotIdMapping.put(regNumber, slotId);

            System.out.println("Allocated slot number: " + slotId);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }


    public void leave(int slotId) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        if (freeSlots.contains(slotId)) {
            throw new DataLayerException("No vehicle is allotted to slot no. " + slotId);
        }
        try {
            Slot slot = slotIdToSlotMapping.get(slotId);
            slot.setOccupied(false);
            regNumberToSlotIdMapping.remove(slot.getCarDetails().getRegistrationNumber());
            colourToSlotIdMapping.get(slot.getCarDetails().getColour()).remove(slotId);
            freeSlots.add(slotId);
            System.out.println("Slot number " + slotId + " is free");
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public void status() throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        System.out.println("Slot No.  Registration No  Colour");
        for (Slot slot : slotIdToSlotMapping.values()) {
            if (slot.isOccupied()) {
                System.out.println(slot.getId() + "  " + slot.getCarDetails().getRegistrationNumber() + "  " + slot.getCarDetails().getColour());
            }
        }
    }

    public void registrationNumbersForCarsWithColour(String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (colourToSlotIdMapping.containsKey(colour)) {
                int count = 0;
                for (int slotId : colourToSlotIdMapping.get(colour)) {
                    if (count > 0) {
                        System.out.print(", ");
                    }
                    count++;
                    System.out.print(slotIdToSlotMapping.get(slotId).getCarDetails().getRegistrationNumber());
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public void slotNumbersForCarsWithColour(String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (colourToSlotIdMapping.containsKey(colour)) {
                int count = 0;
                for (int slotId : colourToSlotIdMapping.get(colour)) {
                    if (count > 0) {
                        System.out.print(", ");
                    }
                    count++;
                    System.out.print(slotId);
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public void slotNumberForRegistrationNumber(String regNumber) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (regNumberToSlotIdMapping.containsKey(regNumber)) {
                System.out.println(regNumberToSlotIdMapping.get(regNumber));
            } else {
                System.out.println("Not found");
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }
}
