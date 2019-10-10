package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParkingSlotServiceImplTest {

    private static final String VEHICLE_REG_NUMBER = "HR-31-0799";
    private static final String VEHICLE_COLOR = "Red";

    private ParkingSlotServiceImpl parkingSlotService;

    @Before
    public void init() {
        this.parkingSlotService = new ParkingSlotServiceImpl();
        parkingSlotService.initParkingSlot(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithNullVehicle() {
        parkingSlotService.parkVehicle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidVehicle() {
        parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, null));
    }

    @Test
    public void testParkVehicleWithParkingFull() {
        parkingSlotService.initParkingSlot(2);
        parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotService.parkVehicle(new Vehicle("KA-01-1111", "Green"));

        final int parkedSlot = parkingSlotService.parkVehicle(new Vehicle("KA-01-1231", "Blue"));

        Assert.assertEquals(-1, parkedSlot);
    }

    @Test
    public void testParkVehicleWithValidParams() {
        final int firstParkingSlot = parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        Assert.assertEquals(1, firstParkingSlot);

        final int secondParkingSlot = parkingSlotService.parkVehicle(new Vehicle("KA-01-1111", "Green"));
        Assert.assertEquals(2, secondParkingSlot);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidSlot() {
        parkingSlotService.unParkVehicle(-1);
    }

    @Test
    public void testUnParkVehicleWithInvalidSlotNumber() {
        final boolean unParked = parkingSlotService.unParkVehicle(11);

        Assert.assertFalse(unParked);
    }

    @Test
    public void testUnParkVehicleWithEmptySlot() {
        final boolean unParked = parkingSlotService.unParkVehicle(1);

        Assert.assertFalse(unParked);
    }

    @Test
    public void testUnParkVehicleWithValidSlot() {
        final int parkedSlotNumber = parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        final boolean unParked = parkingSlotService.unParkVehicle(parkedSlotNumber);

        Assert.assertTrue(unParked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotPositionsForVehicleColorWithInvalidColor() {
        parkingSlotService.getSlotPositionsForVehicleColor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotPositionsForVehicleColorWithNonExistentColor() {
        parkingSlotService.getSlotPositionsForVehicleColor(VEHICLE_COLOR);
    }

    @Test
    public void testGetSlotPositionsForVehicleColorWithValidParams() {
        final int firstVehicleSlot = parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        final int secondVehicleSlot = parkingSlotService.parkVehicle(new Vehicle("KA-11A-1234", VEHICLE_COLOR));

        final List<Integer> slotPositionsForVehicleColor = parkingSlotService.getSlotPositionsForVehicleColor(VEHICLE_COLOR);

        Assert.assertNotNull(slotPositionsForVehicleColor);
        Assert.assertEquals(2, slotPositionsForVehicleColor.size());
        Assert.assertEquals(firstVehicleSlot, (int) slotPositionsForVehicleColor.get(0));
        Assert.assertEquals(secondVehicleSlot, (int) slotPositionsForVehicleColor.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRegNumbersForVehicleColorWithInvalidColor() {
        parkingSlotService.getRegNumbersForVehicleColor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRegNumbersForVehicleColorNonExistentColor() {
        parkingSlotService.getRegNumbersForVehicleColor(VEHICLE_COLOR);
    }

    @Test
    public void testGetRegNumbersForVehicleColorWithValidParams() {
        final String secondVehicleRegNumber = "KA-11A-1234";

        parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotService.parkVehicle(new Vehicle(secondVehicleRegNumber, VEHICLE_COLOR));

        final List<String> regNumbersForVehicleColor = parkingSlotService.getRegNumbersForVehicleColor(VEHICLE_COLOR);

        Assert.assertNotNull(regNumbersForVehicleColor);
        Assert.assertEquals(2, regNumbersForVehicleColor.size());
        Assert.assertTrue(regNumbersForVehicleColor.contains(VEHICLE_REG_NUMBER));
        Assert.assertTrue(regNumbersForVehicleColor.contains(secondVehicleRegNumber));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotNumberForRegNumberWithInvalidRegNumber() {
        parkingSlotService.getSlotNumberForRegNumber(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotNumberForRegNumberWithNonExistentRegNumber() {
        parkingSlotService.getSlotNumberForRegNumber(VEHICLE_REG_NUMBER);
    }

    @Test
    public void testGetSlotNumberForRegNumberWithValidRegNumber() {
        final int allotedVehicleSlotNumber = parkingSlotService.parkVehicle(new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        final int slotNumberForRegNumber = parkingSlotService.getSlotNumberForRegNumber(VEHICLE_REG_NUMBER);

        Assert.assertEquals(allotedVehicleSlotNumber, slotNumberForRegNumber);
    }

}
