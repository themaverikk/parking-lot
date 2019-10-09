package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotAvailabilityDao;

public class ParkingSlotAvailabilityDaoImpl implements ParkingSlotAvailabilityDao {

    private static final ParkingSlotAvailabilityDao instance = new ParkingSlotAvailabilityDaoImpl();

    public static ParkingSlotAvailabilityDao getInstance() {
        return instance;
    }

    private ParkingSlotAvailabilityDaoImpl() {
    }

    @Override
    public int initParkingSlotsAvailability(final int parkingCapacity) {
        return 0;
    }

    @Override
    public int getNearestAvailableSlot() {
        return 0;
    }

    @Override
    public int occupyParkingSlot(final int slotNumber) {
        return 0;
    }

    @Override
    public int freeParkingSlot(final int slotNumber) {
        return 0;
    }
}
