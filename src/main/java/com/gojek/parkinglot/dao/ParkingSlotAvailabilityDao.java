package com.gojek.parkinglot.dao;

public interface ParkingSlotAvailabilityDao {
    int initParkingSlotsAvailability(int parkingCapacity);

    int getNearestAvailableSlot();

    int occupyParkingSlot(int slotNumber);

    int freeParkingSlot(int slotNumber);
}
