package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotPositionDao;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.VehicleValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingSlotPositionDaoImpl implements ParkingSlotPositionDao {
    private static final ParkingSlotPositionDao instance = new ParkingSlotPositionDaoImpl();

    private List<Vehicle> parkingSlots;

    // package default constructor so that it can be instantiated in unit tests, for actual use we'll be using singleton instance only
    ParkingSlotPositionDaoImpl() {
    }

    public static ParkingSlotPositionDao getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlotPositions(final int parkingCapacity) {
        validateParkingCapacity(parkingCapacity);

        this.parkingSlots = new ArrayList<>(parkingCapacity);
        IntStream.range(0, parkingCapacity).forEach(i -> this.parkingSlots.add(null));
    }

    @Override
    public void parkVehicle(final int slotNumber, final Vehicle vehicle) {
        verifyInitialization();
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);

        // slot is already occupied
        if (this.parkingSlots.get(slotNumber - 1) != null) {
            throw new IllegalArgumentException("Given slot is already occupied");
        }

        this.parkingSlots.set(slotNumber - 1, vehicle);
    }

    @Override
    public Vehicle unParkVehicle(final int slotNumber) {
        verifyInitialization();
        validateSlotNumber(slotNumber);

        // slot is already empty
        if (this.parkingSlots.get(slotNumber - 1) == null) {
            throw new IllegalArgumentException("Given slot is already empty");
        }

        return this.parkingSlots.set(slotNumber - 1, null);
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

    private void validateParkingCapacity(final int parkingCapacity) {
        if (parkingCapacity < 0) {
            throw new IllegalArgumentException("Invalid parking capacity");
        }
    }

}
