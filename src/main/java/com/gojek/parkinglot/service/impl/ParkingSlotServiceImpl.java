package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingSlotService;

import java.util.List;

public class ParkingSlotServiceImpl implements ParkingSlotService {

    private static final ParkingSlotService instance = new ParkingSlotServiceImpl();

    private ParkingSlotServiceImpl() {
    }

    public static ParkingSlotService getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlot(final int slotSize) {

    }

    @Override
    public int parkVehicle(final Vehicle vehicle) {
        return 0;
    }

    @Override
    public boolean unParkVehicle(final int slotNumber) {
        return false;
    }

    @Override
    public List<Integer> getSlotPositionsForVehicleColor(final String vehicleColor) {
        return null;
    }

    @Override
    public int getSlotNumberForRegNumber(final String regNumber) {
        return 0;
    }

    @Override
    public List<ParkingSlot> getParkingStatus() {
        return null;
    }
}
