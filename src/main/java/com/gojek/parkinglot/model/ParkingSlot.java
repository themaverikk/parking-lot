package com.gojek.parkinglot.model;

public class ParkingSlot {
    private int slotNumber;
    private Vehicle parkedVehicle;

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(final int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(final Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}
