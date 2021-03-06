package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingSlotColorInfoDao {
    void initParkingSlotColorInfo();

    boolean parkVehicle(int slotNumber, Vehicle vehicle);

    boolean unParkVehicle(int slotNumber, Vehicle vehicle);

    List<Integer> getSlotPositionsForVehicleColor(String vehicleColor);

    List<String> getRegNumbersForVehicleColor(final String vehicleColor);
}
