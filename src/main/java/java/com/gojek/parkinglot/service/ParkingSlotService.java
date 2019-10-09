package java.com.gojek.parkinglot.service;

import java.com.gojek.parkinglot.model.ParkingSlot;
import java.com.gojek.parkinglot.model.Vehicle;

import java.util.List;

public interface ParkingSlotService {

    void initParkingSlot(int parkingCapacity);

    int parkVehicle(Vehicle vehicle);

    boolean unParkVehicle(int slotNumber);

    List<Integer> getSlotPositionsForVehicleColor(String vehicleColor);

    List<String> getRegNumbersForVehicleColor(final String vehicleColor);

    int getSlotNumberForRegNumber(final String regNumber);

    List<ParkingSlot> getParkingStatus();
}
