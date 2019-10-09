package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.SlotPositionDao;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public void parkVehicle(final int slotNumber, final Vehicle vehicle) {
        verifyInitialization();
        validateSlotNumber(slotNumber);
        validateVehicle(vehicle);

        // slot is already occupied
        if (this.parkingSlots.get(slotNumber - 1) != null) {
            throw new IllegalArgumentException("Given slot is already occupied");
        }

        this.parkingSlots.set(slotNumber - 1, vehicle);
    }

    @Override
    public boolean unParkVehicle(final int slotNumber) {
        verifyInitialization();
        validateSlotNumber(slotNumber);

        // slot is empty occupied
        if (this.parkingSlots.get(slotNumber - 1) == null) {
            throw new IllegalArgumentException("Given slot is already empty");
        }

        this.parkingSlots.set(slotNumber - 1, null);

        // returning boolean for future use case, in case we decide to change the logic and vehicle may or may not be unparked
        return true;
    }

    @Override
    public List<ParkingSlot> getParkingStatus() {
        verifyInitialization();

        return IntStream.range(0, this.parkingSlots.size())
                .filter(i -> this.parkingSlots.get(i) != null)
                .mapToObj(i -> new ParkingSlot(i + 1, this.parkingSlots.get(i)))
                .collect(Collectors.toList());
    }

    private void verifyInitialization() {
        if (this.parkingSlots == null) {
            throw new IllegalStateException("Parking lot has not been initialized properly");
        }
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1 || slotNumber > this.parkingSlots.size()) {
            throw new IllegalArgumentException("Invalid slotNumber");
        }
    }

    private void validateVehicle(final Vehicle vehicle) {
        if (vehicle == null || StringUtils.isBlank(vehicle.getRegNumber()) || StringUtils.isBlank(vehicle.getColor())) {
            throw new IllegalArgumentException("Invalid vehicle object");
        }
    }
}
