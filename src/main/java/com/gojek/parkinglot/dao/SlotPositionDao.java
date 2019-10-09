package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface SlotPositionDao {
    void initParkingSlotPositions(int parkingCapacity);

    int parkVehicle(Vehicle vehicle);

    boolean unParkVehicle(int slotNumber);

    List<ParkingSlot> getParkingStatus();
}