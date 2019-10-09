package java.com.gojek.parkinglot.dao;

public interface ParkingSlotAvailabilityDao {
    void initParkingSlotsAvailability(int parkingCapacity);

    boolean isParkingSlotAvailable();

    boolean isParkingEmpty();

    int getNearestAvailableSlot();

    void occupyParkingSlot(int slotNumber);

    void freeParkingSlot(int slotNumber);
}
