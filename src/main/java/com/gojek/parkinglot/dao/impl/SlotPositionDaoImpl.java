package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.SlotPositionDao;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class SlotPositionDaoImpl implements SlotPositionDao {
    private static final SlotPositionDao instance = new SlotPositionDaoImpl();

    private List<Vehicle> parkingSlots;

    private SlotPositionDaoImpl() {
    }

    public static SlotPositionDao getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlotPositions(final int parkingCapacity) {
        this.parkingSlots = new ArrayList<>(parkingCapacity);
    }

    @Override
    public int parkVehicle(final Vehicle vehicle) {
        verifyInitialization();

        return 0;
    }

    @Override
    public boolean unParkVehicle(final int slotNumber) {
        return false;
    }

    @Override
    public List<ParkingSlot> getParkingStatus() {
        return null;
    }

    private void verifyInitialization() {
        if (this.parkingSlots == null) {
            throw new IllegalStateException("Parking lot has not been initialized properly");
        }
    }
}
