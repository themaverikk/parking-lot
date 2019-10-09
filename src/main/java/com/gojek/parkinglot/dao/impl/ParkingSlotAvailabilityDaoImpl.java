package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotAvailabilityDao;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class ParkingSlotAvailabilityDaoImpl implements ParkingSlotAvailabilityDao {

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
        IntStream.range(1, parkingCapacity + 1).forEach(i -> this.emptyParkingSlotsOrder.offer(i));
    }

    @Override
    public int getNearestAvailableSlot() {
        return this.emptyParkingSlotsOrder.peek();
    }

    @Override
    public void occupyParkingSlot(final int slotNumber) {
        validateSlotNumber(slotNumber);

        // this flat will the parkingSlot was available or not
        final boolean removed = this.emptyParkingSlotsOrder.remove(slotNumber);

        if (!removed) {
            throw new IllegalArgumentException("given slot was not empty");
        }

    }

    @Override
    public void freeParkingSlot(final int slotNumber) {
        validateSlotNumber(slotNumber);

        this.emptyParkingSlotsOrder.add(slotNumber);
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("slotNumber is invalid");
        }
    }
}
