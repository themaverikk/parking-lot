package com.gojek.parkinglot.dao;

public interface ParkingSlotAvailabilityDao {
    void initParkingSlotsAvailability(int parkingCapacity);

    int getNearestAvailableSlot();

    void occupyParkingSlot(int slotNumber);

    void freeParkingSlot(int slotNumber);
}
