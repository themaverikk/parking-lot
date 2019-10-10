package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParkingSlotColorInfoDaoImplTest {

    private static final String VEHICLE_REG_NUMBER = "HR-31-0799";
    private static final String VEHICLE_COLOR = "Red";

    private ParkingSlotColorInfoDaoImpl parkingSlotColorInfoDao;

    @Before
    public void init() {
        this.parkingSlotColorInfoDao = new ParkingSlotColorInfoDaoImpl();
        this.parkingSlotColorInfoDao.initParkingSlotColorInfo();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidSlot() {
        parkingSlotColorInfoDao.parkVehicle(-1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithNullVehicle() {
        parkingSlotColorInfoDao.parkVehicle(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidVehicle() {
        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(null, VEHICLE_COLOR));
    }

    @Test
    public void testParkVehicleWithValidParams() {
        final boolean parked = parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));

        Assert.assertTrue(parked);
    }

    @Test
    public void testParkVehicleWithDuplicateParking() {
        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        final boolean parked = parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));

        Assert.assertFalse(parked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidSlot() {
        parkingSlotColorInfoDao.unParkVehicle(-1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithNullVehicle() {
        parkingSlotColorInfoDao.unParkVehicle(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidVehicle() {
        parkingSlotColorInfoDao.unParkVehicle(1, new Vehicle(null, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithDuplicateUnParking() {
        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotColorInfoDao.unParkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotColorInfoDao.unParkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test
    public void testUnParkVehicleWithValidParams() {
        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        final boolean unParked = parkingSlotColorInfoDao.unParkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));

        Assert.assertTrue(unParked);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotPositionsForVehicleColorWithInvalidColor() {
        parkingSlotColorInfoDao.getSlotPositionsForVehicleColor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotPositionsForVehicleColorWithNonExistentColor() {
        parkingSlotColorInfoDao.getSlotPositionsForVehicleColor(VEHICLE_COLOR);
    }

    @Test
    public void testGetSlotPositionsForVehicleColorWithValidColor() {
        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotColorInfoDao.parkVehicle(3, new Vehicle("KA-01-1111", VEHICLE_COLOR));

        final List<Integer> slotPositionsForVehicleColor = parkingSlotColorInfoDao.getSlotPositionsForVehicleColor(VEHICLE_COLOR);

        Assert.assertNotNull(slotPositionsForVehicleColor);
        Assert.assertEquals(2, slotPositionsForVehicleColor.size());
        Assert.assertEquals(1, (int) slotPositionsForVehicleColor.get(0));
        Assert.assertEquals(3, (int) slotPositionsForVehicleColor.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRegNumbersForVehicleColorWithInvalidColor() {
        parkingSlotColorInfoDao.getRegNumbersForVehicleColor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRegNumbersForVehicleColorWithNonExistentColor() {
        parkingSlotColorInfoDao.getSlotPositionsForVehicleColor(VEHICLE_COLOR);
    }

    @Test
    public void testGetRegNumbersForVehicleColorWithValidColor() {
        final String secondVehicleRegNumber = "KA-01-1111";

        parkingSlotColorInfoDao.parkVehicle(1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
        parkingSlotColorInfoDao.parkVehicle(3, new Vehicle(secondVehicleRegNumber, VEHICLE_COLOR));

        final List<String> slotPositionsForVehicleColor = parkingSlotColorInfoDao.getRegNumbersForVehicleColor(VEHICLE_COLOR);

        Assert.assertNotNull(slotPositionsForVehicleColor);
        Assert.assertEquals(2, slotPositionsForVehicleColor.size());
        Assert.assertEquals(VEHICLE_REG_NUMBER, slotPositionsForVehicleColor.get(0));
        Assert.assertEquals(secondVehicleRegNumber, slotPositionsForVehicleColor.get(1));
    }

}
