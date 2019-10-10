package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingSlotRegNumberInfoDaoImplTest {

    private static final String VEHICLE_REG_NUMBER = "HR-31-0799";
    private static final String VEHICLE_COLOR = "Red";

    private ParkingSlotRegNumberInfoDaoImpl parkingSlotRegNumberInfoDao;

    @Before
    public void init() {
        this.parkingSlotRegNumberInfoDao = new ParkingSlotRegNumberInfoDaoImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidSlotNumber() {
        parkingSlotRegNumberInfoDao.parkVehicle(-1, new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithNullVehicle() {
        parkingSlotRegNumberInfoDao.parkVehicle(-1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidVehicleRegNumber() {
        parkingSlotRegNumberInfoDao.parkVehicle(-1, new Vehicle(null, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithInvalidVehicleColor() {
        parkingSlotRegNumberInfoDao.parkVehicle(-1, new Vehicle(VEHICLE_REG_NUMBER, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParkVehicleWithDoublePark() {
        final Vehicle vehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        parkingSlotRegNumberInfoDao.parkVehicle(1, vehicle);
        parkingSlotRegNumberInfoDao.parkVehicle(2, vehicle);
    }

    @Test
    public void testParkVehicleWithValidParams() {
        final Vehicle vehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        parkingSlotRegNumberInfoDao.parkVehicle(1, vehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithNullVehicle() {
        parkingSlotRegNumberInfoDao.unParkVehicle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidVehicleRegNumber() {
        parkingSlotRegNumberInfoDao.unParkVehicle(new Vehicle(null, VEHICLE_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithInvalidVehicleColor() {
        parkingSlotRegNumberInfoDao.unParkVehicle(new Vehicle(VEHICLE_REG_NUMBER, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithUnParkedVehicle() {
        final Vehicle vehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);

        parkingSlotRegNumberInfoDao.unParkVehicle(vehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnParkVehicleWithDoubleUnpark() {
        final Vehicle vehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        parkingSlotRegNumberInfoDao.parkVehicle(1, vehicle);

        parkingSlotRegNumberInfoDao.unParkVehicle(vehicle);
        parkingSlotRegNumberInfoDao.unParkVehicle(vehicle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotNumberWithNullRegNumber() {
        parkingSlotRegNumberInfoDao.getSlotNumberForRegNumber(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSlotNumberWithNonParkedRegNumber() {
        parkingSlotRegNumberInfoDao.getSlotNumberForRegNumber(VEHICLE_REG_NUMBER);
    }

    @Test
    public void testGetSlotNumberForRegNumberWithValidParams() {
        final int expectedSlotNumber = 1;

        final Vehicle vehicle = new Vehicle(VEHICLE_REG_NUMBER, VEHICLE_COLOR);
        parkingSlotRegNumberInfoDao.parkVehicle(expectedSlotNumber, vehicle);
        final int actualSlotNumber = parkingSlotRegNumberInfoDao.getSlotNumberForRegNumber(VEHICLE_REG_NUMBER);

        Assert.assertEquals(expectedSlotNumber, actualSlotNumber);
    }

}
