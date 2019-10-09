package java.com.gojek.parkinglot.dao;

import java.com.gojek.parkinglot.model.ParkingSlot;
import java.com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingSlotPositionDao {
    void initParkingSlotPositions(int parkingCapacity);

    void parkVehicle(int slotNumber, Vehicle vehicle);

    Vehicle unParkVehicle(int slotNumber);

    List<ParkingSlot> getParkingStatus();
}
