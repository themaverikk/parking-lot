package java.com.gojek.parkinglot.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingSlotTest {
    public static final String VEHICLE_COLOR = "Black";
    public static final String VEHICLE_NUMBER = "KA-09-FN-1234";
    public static final int SLOT_NUMBER = 123;
    private ParkingSlot parkingSlot;

    @Before
    public void setUp(){
        parkingSlot = new ParkingSlot();
        Vehicle vehicle = new Vehicle();
        vehicle.setColor(VEHICLE_COLOR);
        vehicle.setRegNumber(VEHICLE_NUMBER);
        parkingSlot.setParkedVehicle(vehicle);
        parkingSlot.setSlotNumber(SLOT_NUMBER);
    }

    @Test
    public void getSlotNumber() throws Exception {
        assertEquals(SLOT_NUMBER, parkingSlot.getSlotNumber());
    }


    @Test
    public void getParkedVehicle() throws Exception {
        assertEquals(VEHICLE_COLOR, parkingSlot.getParkedVehicle().getColor() );
        assertEquals(VEHICLE_NUMBER, parkingSlot.getParkedVehicle().getRegNumber());
    }


}