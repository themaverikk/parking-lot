package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}
