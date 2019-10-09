package java.com.gojek.parkinglot.dao;

import java.com.gojek.parkinglot.model.Vehicle;

public interface ParkingSlotRegNumberInfoDao {
    void parkVehicle(int slotNumber, Vehicle vehicle);

    void unParkVehicle(Vehicle vehicle);

    int getSlotNumberForRegNumber(String regNumber);
}
