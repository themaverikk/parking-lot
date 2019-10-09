package java.com.gojek.parkinglot.model;

public class ParkingSlot {
    private int slotNumber;
    private Vehicle parkedVehicle;

    public ParkingSlot() {
    }

    public ParkingSlot(final int slotNumber, final Vehicle parkedVehicle) {
        this.slotNumber = slotNumber;
        this.parkedVehicle = parkedVehicle;
    }

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
