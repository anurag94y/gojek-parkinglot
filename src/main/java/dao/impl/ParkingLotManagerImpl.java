package dao.impl;

import dao.ParkingLotManager;
import entities.Car;
import entities.Slot;
import exception.DataLayerException;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLotManagerImpl implements ParkingLotManager {
    private Queue<Integer> freeSlots = new PriorityQueue<>();
    private Map<String, Set<Integer>> colourToSlotIdMapping = new HashMap<>();
    private Map<String, Integer> regNumberToSlotIdMapping = new HashMap<>();
    private Map<Integer, Slot> slotIdToSlotMapping = new HashMap<>();
    private boolean isParkingLotCreated = false;

    public boolean createParkingLot(int slots) throws DataLayerException {
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
            isParkingLotCreated = true;
            return true;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public String park(String regNumber, String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (freeSlots.isEmpty()) {
                return "Sorry, parking lot is full";
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
            return "Allocated slot number: " + slotId;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }


    public boolean leave(int slotId) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        if (freeSlots.contains(slotId)) {
            throw new DataLayerException("No vehicle is allotted to slot no. " + slotId);
        }
        if (slotId > slotIdToSlotMapping.size() || slotId <= 0) {
            throw new DataLayerException("Invalid slotId");
        }
        try {
            Slot slot = slotIdToSlotMapping.get(slotId);
            slot.setOccupied(false);
            regNumberToSlotIdMapping.remove(slot.getCarDetails().getRegistrationNumber());
            colourToSlotIdMapping.get(slot.getCarDetails().getColour()).remove(slotId);
            freeSlots.add(slotId);
            return true;
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public List<String> status() throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        return slotIdToSlotMapping.values().stream().filter(t -> t.isOccupied()).map(t ->
                t.getId() + "\t" + t.getCarDetails().getRegistrationNumber() + "\t" +  t.getCarDetails().getColour()).collect(Collectors.toList());
    }

    public List<String> registrationNumbersForCarsWithColour(String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (colourToSlotIdMapping.containsKey(colour)) {
                return colourToSlotIdMapping.get(colour).stream().map(t -> slotIdToSlotMapping.get(t).getCarDetails().getRegistrationNumber()).collect(Collectors.toList());
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public List<Integer> slotNumbersForCarsWithColour(String colour) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (colourToSlotIdMapping.containsKey(colour)) {
                return colourToSlotIdMapping.get(colour).stream().collect(Collectors.toList());
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    public Integer slotNumberForRegistrationNumber(String regNumber) throws DataLayerException {
        if (!isParkingLotCreated) {
            throw new DataLayerException("ParkingLot not created yet");
        }
        try {
            if (regNumberToSlotIdMapping.containsKey(regNumber)) {
                return regNumberToSlotIdMapping.get(regNumber);
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }
}
