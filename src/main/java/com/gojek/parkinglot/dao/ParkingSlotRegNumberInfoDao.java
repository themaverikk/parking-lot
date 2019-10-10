package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.Vehicle;

public interface ParkingSlotRegNumberInfoDao {
    void initParkingSlotRegNumberInfo();

    void parkVehicle(int slotNumber, Vehicle vehicle);

    void unParkVehicle(Vehicle vehicle);

    int getSlotNumberForRegNumber(String regNumber);
}
