package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingSlotPositionDao {
    void initParkingSlotPositions(int parkingCapacity);

    void parkVehicle(int slotNumber, Vehicle vehicle);

    Vehicle unParkVehicle(int slotNumber);

    List<ParkingSlot> getParkingStatus();
}
