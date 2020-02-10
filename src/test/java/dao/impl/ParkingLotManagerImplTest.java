package dao.impl;

import dao.ParkingLotManager;
import exception.DataLayerException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingLotManagerImplTest {

    private ParkingLotManager parkingLotManager;

    @Before
    public void setUp() {
        parkingLotManager = new ParkingLotManagerImpl();
    }

    @Test(expected = DataLayerException.class)
    public void createParkingLotAgain() throws DataLayerException {
        parkingLotManager.createParkingLot(6);
        parkingLotManager.createParkingLot(6);
    }

    @Test(expected = DataLayerException.class)
    public void parkWithoutCreatingParkingLot() throws DataLayerException {
        parkingLotManager.park("RegNumber", "colour");
    }

    @Test
    public void parkWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        String response = parkingLotManager.park("RegNumber", "Colour");
        assertEquals("Allocated slot number: " + 1, response);
    }

    @Test
    public void parkWithAllUsedSlots() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("RegNumber1", "Colour");
        String response = parkingLotManager.park("RegNumber2", "Colour");
        assertEquals("Sorry, parking lot is full", response);
    }

    @Test(expected = DataLayerException.class)
    public void leaveWithoutCreatingParkingLot() throws DataLayerException {
        parkingLotManager.leave(2);
    }

    @Test(expected = DataLayerException.class)
    public void leaveWithAlreadyFreeSlotId() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.leave(1);
    }

    @Test(expected = DataLayerException.class)
    public void leaveWithInvalidSlotId() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.leave(2);
    }

    @Test
    public void leaveWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "Colour");
        assertTrue(parkingLotManager.leave(1));
    }

    @Test(expected = DataLayerException.class)
    public void statusWithoutCreatingParkingSlot() throws DataLayerException {
        parkingLotManager.status();
    }

    @Test
    public void statusWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        List<String> expected = new ArrayList<>();
        expected.add("1\tregNumber\tcolour");
        assertEquals(expected, parkingLotManager.status());
    }

    @Test(expected = DataLayerException.class)
    public void registrationNumbersForCarsWithColourWithoutCreatingParkingSlot() throws DataLayerException {
        parkingLotManager.registrationNumbersForCarsWithColour("colour");
    }

    @Test
    public void registrationNumbersForCarsWithColourWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(Collections.singletonList("regNumber"), parkingLotManager.registrationNumbersForCarsWithColour("colour"));
    }

    @Test
    public void registrationNumbersForCarsWithColourWithinvalidColour() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(Collections.EMPTY_LIST, parkingLotManager.registrationNumbersForCarsWithColour("colour1"));
    }

    @Test(expected = DataLayerException.class)
    public void slotNumbersForCarsWithColourWithoutCreatingParkingSlot() throws DataLayerException {
        parkingLotManager.slotNumbersForCarsWithColour("colour");
    }

    @Test
    public void slotNumbersForCarsWithColourWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(Collections.singletonList(1), parkingLotManager.slotNumbersForCarsWithColour("colour"));
    }

    @Test
    public void slotNumbersForCarsWithColourWithinvalidColour() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(Collections.EMPTY_LIST, parkingLotManager.slotNumbersForCarsWithColour("colour1"));
    }

    @Test(expected = DataLayerException.class)
    public void slotNumberForRegistrationNumberWithoutCreatingParkingSlot() throws DataLayerException {
        parkingLotManager.slotNumberForRegistrationNumber("regNumber");
    }

    @Test
    public void slotNumberForRegistrationNumberWithHappyCase() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(1, parkingLotManager.slotNumberForRegistrationNumber("regNumber").intValue());
    }

    @Test
    public void sslotNumberForRegistrationNumberWithinvalidColour() throws DataLayerException {
        parkingLotManager.createParkingLot(1);
        parkingLotManager.park("regNumber", "colour");
        assertEquals(0, parkingLotManager.slotNumberForRegistrationNumber("regNumber1").intValue());
    }
}