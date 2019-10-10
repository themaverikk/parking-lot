package com.gojek.parkinglot.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingSlotAvailabilityDaoImplTest {

    private ParkingSlotAvailabilityDaoImpl parkingSlotAvailabilityDao;

    @Before
    public void init() {
        this.parkingSlotAvailabilityDao = new ParkingSlotAvailabilityDaoImpl();
        this.parkingSlotAvailabilityDao.initParkingSlotsAvailability(10);
    }

    @Test
    public void testIsParkingSlotAvailableSuccess() {
        final boolean parkingSlotAvailable = parkingSlotAvailabilityDao.isParkingSlotAvailable();

        Assert.assertTrue(parkingSlotAvailable);
    }

    @Test
    public void testIsParkingSlotAvailableNegative() {
        parkingSlotAvailabilityDao.initParkingSlotsAvailability(2);

        parkingSlotAvailabilityDao.occupyParkingSlot(1);
        parkingSlotAvailabilityDao.occupyParkingSlot(2);

        final boolean parkingSlotAvailable = parkingSlotAvailabilityDao.isParkingSlotAvailable();

        Assert.assertFalse(parkingSlotAvailable);
    }

    @Test
    public void testEmptyParking() {
        final boolean parkingEmpty = parkingSlotAvailabilityDao.isParkingEmpty();

        Assert.assertTrue(parkingEmpty);
    }

    @Test
    public void testNoEmptyParking() {
        parkingSlotAvailabilityDao.occupyParkingSlot(1);
        final boolean parkingEmpty = parkingSlotAvailabilityDao.isParkingEmpty();

        Assert.assertFalse(parkingEmpty);
    }

    @Test
    public void testGetNearestAvailableSlotWithEmptyParking() {
        final int nearestAvailableSlot = parkingSlotAvailabilityDao.getNearestAvailableSlot();

        Assert.assertEquals(1, nearestAvailableSlot);
    }

    @Test
    public void testGetNearestAvailableSlotWithNonEmptyParking() {
        parkingSlotAvailabilityDao.occupyParkingSlot(1);
        parkingSlotAvailabilityDao.occupyParkingSlot(2);

        final int nearestAvailableSlot = parkingSlotAvailabilityDao.getNearestAvailableSlot();

        Assert.assertEquals(3, nearestAvailableSlot);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOccupyParkingSlotWithInvalidSlotNumber() {
        parkingSlotAvailabilityDao.occupyParkingSlot(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOccupyParkingSlotWithNonOptimalSlotNumber() {
        parkingSlotAvailabilityDao.occupyParkingSlot(2);
    }

    @Test
    public void testOccupyParkingSlotWithValidParams() {
        parkingSlotAvailabilityDao.occupyParkingSlot(1);
        parkingSlotAvailabilityDao.occupyParkingSlot(2);
        parkingSlotAvailabilityDao.occupyParkingSlot(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFreeParkingSlotWithInvalidSlotNumber() {
        parkingSlotAvailabilityDao.freeParkingSlot(-1);
    }

    @Test
    public void testFreeParkingSlotWithValidParams() {
        parkingSlotAvailabilityDao.occupyParkingSlot(1);
        parkingSlotAvailabilityDao.occupyParkingSlot(2);
        parkingSlotAvailabilityDao.freeParkingSlot(2);
        parkingSlotAvailabilityDao.freeParkingSlot(1);
    }
}
