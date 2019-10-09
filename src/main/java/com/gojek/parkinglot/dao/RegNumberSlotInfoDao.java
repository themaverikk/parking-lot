package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.Vehicle;

public interface RegNumberSlotInfoDao {
    int parkVehicle(int slotNumber, Vehicle vehicle);

    boolean unParkVehicle(int slotNumber, Vehicle vehicle);

    int getSlotNumberForRegNumber(String regNumber);
}
