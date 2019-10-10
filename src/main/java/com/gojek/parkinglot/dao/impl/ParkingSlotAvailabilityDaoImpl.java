package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotAvailabilityDao;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class ParkingSlotAvailabilityDaoImpl implements ParkingSlotAvailabilityDao {

    private int parkingCapacity;
    private Queue<Integer> emptyParkingSlotsOrder;

    private static final ParkingSlotAvailabilityDao instance = new ParkingSlotAvailabilityDaoImpl();

    public static ParkingSlotAvailabilityDao getInstance() {
        return instance;
    }

    // package default constructor so that it can be instantiated in unit tests, for actual use we'll be using singleton instance only
    ParkingSlotAvailabilityDaoImpl() {
    }

    @Override
    public void initParkingSlotsAvailability(final int parkingCapacity) {
        this.emptyParkingSlotsOrder = new PriorityQueue<>();
        this.parkingCapacity = parkingCapacity;
        IntStream.range(1, parkingCapacity + 1).forEach(i -> this.emptyParkingSlotsOrder.offer(i));
    }

    @Override
    public boolean isParkingSlotAvailable() {
        verifyInitialization();
        return !this.emptyParkingSlotsOrder.isEmpty();
    }

    @Override
    public boolean isParkingEmpty() {
        verifyInitialization();
        return this.emptyParkingSlotsOrder.size() == parkingCapacity;
    }

    @Override
    public int getNearestAvailableSlot() {
        verifyInitialization();
        return this.emptyParkingSlotsOrder.peek();
    }

    @Override
    public void occupyParkingSlot(final int slotNumber) {
        verifyInitialization();
        validateSlotNumber(slotNumber);

        if (slotNumber != this.emptyParkingSlotsOrder.peek()) {
            throw new IllegalArgumentException("given slot can't be occupied");

        }
        this.emptyParkingSlotsOrder.remove(slotNumber);
    }

    @Override
    public void freeParkingSlot(final int slotNumber) {
        verifyInitialization();
        validateSlotNumber(slotNumber);

        this.emptyParkingSlotsOrder.add(slotNumber);
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("slotNumber is invalid");
        }
    }

    private void verifyInitialization() {
        if (this.emptyParkingSlotsOrder == null) {
            throw new IllegalStateException("Parking lot has not been initialized properly");
        }
    }
}
