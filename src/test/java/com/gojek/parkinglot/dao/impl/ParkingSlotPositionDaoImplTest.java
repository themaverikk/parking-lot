package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParkingSlotPositionDaoImplTest {

    private static final String VEHICLE_REG_NUMBER = "HR-31-0799";
    private static final String VEHICLE_COLOR = "Red";

    private ParkingSlotPositionDaoImpl parkingSlotPositionDao;

    @Before
    public void init() {
        parkingSlotPositionDao = new ParkingSlotPositionDaoImpl();
        parkingSlotPositionDao.initParkingSlotPositions(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitParkingSlotPositionsWithInvalidCapacity() {
        parkingSlotPositionDao.initParkingSlotPositions(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidSlot() {
        parkingSlotPositionDao.parkVehicle(-2, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithNullVehicle() {
        parkingSlotPositionDao.parkVehicle(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidVehicle() {
        parkingSlotPositionDao.parkVehicle(1, new Vehicle());
    }

    @Test
    public void testParkVehicleWithValidParams() {
        parkingSlotPositionDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidSlot() {
        parkingSlotPositionDao.unParkVehicle(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithSlotBeyondCapacity() {
        parkingSlotPositionDao.unParkVehicle(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleFromEmptySlot() {
        parkingSlotPositionDao.parkVehicle(2, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotPositionDao.unParkVehicle(1);
    }

    @Test
    public void testUnParkVehicleWithValidParams() {
        final Vehicle parkedVehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        parkingSlotPositionDao.parkVehicle(1, parkedVehicle);

        final Vehicle unParkedVehicle = parkingSlotPositionDao.unParkVehicle(1);

        Assert.assertNotNull(unParkedVehicle);
        Assert.assertEquals(parkedVehicle, unParkedVehicle);
    }

    @Test
    public void testGetParkingStatusWithEmptyParking() {
        final List<ParkingSlot> parkingStatus = parkingSlotPositionDao.getParkingStatus();

        Assert.assertNotNull(parkingStatus);
        Assert.assertTrue(parkingStatus.isEmpty());
    }

    @Test
    public void testGetParkingStatusWithFilledSlots() {
        final Vehicle vehicle1 = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        final Vehicle vehicle2 = new Vehicle("KA-12-3234", "Green");
        final Vehicle vehicle3 = new Vehicle("TA-31A-4099", "Yellow");

        parkingSlotPositionDao.parkVehicle(1, vehicle1);
        parkingSlotPositionDao.parkVehicle(2, vehicle2);
        parkingSlotPositionDao.parkVehicle(3, vehicle3);
        parkingSlotPositionDao.unParkVehicle(2);

        final List<ParkingSlot> parkingStatus = parkingSlotPositionDao.getParkingStatus();

        Assert.assertNotNull(parkingStatus);
        Assert.assertEquals(2, parkingStatus.size());

        final ParkingSlot firstParkingSlot = parkingStatus.get(0);
        Assert.assertNotNull(firstParkingSlot);
        Assert.assertEquals(1, firstParkingSlot.getSlotNumber());
        Assert.assertEquals(vehicle1, firstParkingSlot.getParkedVehicle());

        final ParkingSlot secondParkingSlot = parkingStatus.get(1);
        Assert.assertEquals(3, secondParkingSlot.getSlotNumber());
        Assert.assertEquals(vehicle3, secondParkingSlot.getParkedVehicle());
    }

}
