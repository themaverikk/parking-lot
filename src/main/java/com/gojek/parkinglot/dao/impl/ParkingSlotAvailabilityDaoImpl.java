package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotAvailabilityDao;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class ParkingSlotAvailabilityDaoImpl implements ParkingSlotAvailabilityDao {

    private int parkingCapacity;
    private final Queue<Integer> emptyParkingSlotsOrder;

    private static final ParkingSlotAvailabilityDao instance = new ParkingSlotAvailabilityDaoImpl();

    public static ParkingSlotAvailabilityDao getInstance() {
        return instance;
    }

    private ParkingSlotAvailabilityDaoImpl() {
        emptyParkingSlotsOrder = new PriorityQueue<>();
    }

    @Override
    public void initParkingSlotsAvailability(final int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        IntStream.range(1, parkingCapacity + 1).forEach(i -> this.emptyParkingSlotsOrder.offer(i));
    }

    @Override
    public boolean isParkingSlotAvailable() {
        return !this.emptyParkingSlotsOrder.isEmpty();
    }

    @Override
    public boolean isParkingEmpty() {
        return this.emptyParkingSlotsOrder.size() == parkingCapacity;
    }

    @Override
    public int getNearestAvailableSlot() {
        return this.emptyParkingSlotsOrder.peek();
    }

    @Override
    public void occupyParkingSlot(final int slotNumber) {
        validateSlotNumber(slotNumber);

        // this flag will the parkingSlot was available or not
        final boolean removed = this.emptyParkingSlotsOrder.remove(slotNumber);

        if (!removed) {
            throw new IllegalArgumentException("given slot is not empty");
        }

    }

    @Override
    public void freeParkingSlot(final int slotNumber) {
        validateSlotNumber(slotNumber);

        // flag to specify whether slot is already free
        final boolean added = this.emptyParkingSlotsOrder.add(slotNumber);

        if (!added) {
            throw new IllegalArgumentException("given slot is already free");
        }
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("slotNumber is invalid");
        }
    }
}
