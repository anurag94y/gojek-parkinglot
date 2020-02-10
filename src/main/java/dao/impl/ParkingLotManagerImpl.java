package dao.impl;

import dao.ParkingLotManager;
import entities.Car;
import entities.Slot;

import java.util.*;

public class ParkingLotManagerImpl implements ParkingLotManager {
    private Queue<Integer> freeSlots = new PriorityQueue<>();
    private Map<String, Set<Integer>> colourToSlotIdMapping = new HashMap<>();
    private Map<String, Integer> regNumberToSlotIdMapping = new HashMap<>();
    private Map<Integer, Slot> slotIdToSlotMapping = new HashMap<>();
    private boolean isParkingLotCreated = false;

    public void createParkingLot(int slots) {
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
    }

    public void park(String regNumber, String colour) {
        if (freeSlots.isEmpty()) {
            System.out.println("Sorry, parking lot is full");
            return ;
        }
        int slotId = freeSlots.poll();
        slotIdToSlotMapping.get(slotId).setCarDetails(new Car(regNumber, colour));
        slotIdToSlotMapping.get(slotId).setOccupied(true);

        if (!colourToSlotIdMapping.containsKey(colour)) {
            colourToSlotIdMapping.put(colour, new HashSet<>());
        }
        colourToSlotIdMapping.compute(colour, (k,v) -> { v.add(slotId); return v; });
        regNumberToSlotIdMapping.put(regNumber, slotId);

        System.out.println("Allocated slot number: " + slotId);
    }


    public void leave(int slotId) {
        if (freeSlots.contains(slotId)) {
            //Already Free
        }
        Slot slot = slotIdToSlotMapping.get(slotId);
        slot.setOccupied(false);
        removeSlotIdFromColourToSlotIdMapping(slot.getCarDetails().getColour(), slotId);
        regNumberToSlotIdMapping.remove(slot.getCarDetails().getRegistrationNumber());
        freeSlots.add(slotId);
        System.out.println("Slot number " + slotId + " is free");
    }

    private void removeSlotIdFromColourToSlotIdMapping(String colour, int slotId) {
        if (colourToSlotIdMapping.containsKey(colour) && colourToSlotIdMapping.get(colour).contains(slotId) ) {
            colourToSlotIdMapping.get(colour).remove(slotId);
        } else {
//            throw new Exception();
        }
    }

    public void status() {
        System.out.println("Slot No.  Registration No  Colour");
        for (Slot slot : slotIdToSlotMapping.values()) {
            if (slot.isOccupied()) {
                System.out.println(slot.getId() + "  " + slot.getCarDetails().getRegistrationNumber() + "  " +  slot.getCarDetails().getColour() );
            }
        }
    }

    public void registrationNumbersForCarsWithColour(String colour) {
        if (colourToSlotIdMapping.containsKey(colour)) {
            int count = 0;
            for (int slotId : colourToSlotIdMapping.get(colour)) {
                if ( count > 0 ) {
                    System.out.print(", ");
                }
                count++;
                System.out.print(slotIdToSlotMapping.get(slotId).getCarDetails().getRegistrationNumber());
            }
            System.out.println();
        }
    }

    public void slotNumbersForCarsWithColour(String colour) {
        if (colourToSlotIdMapping.containsKey(colour)) {
            int count = 0;
            for (int slotId : colourToSlotIdMapping.get(colour)) {
                if ( count > 0 ) {
                    System.out.print(", ");
                }
                count++;
                System.out.print(slotId);
            }
            System.out.println();
        }
    }

    public void slotNumberForRegistrationNumber(String regNumber) {
        if (regNumberToSlotIdMapping.containsKey(regNumber)) {
            System.out.println(regNumberToSlotIdMapping.get(regNumber));
        } else {
            System.out.println("Not found");
        }
    }
}
