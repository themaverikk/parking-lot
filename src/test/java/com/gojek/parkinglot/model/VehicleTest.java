package java.com.gojek.parkinglot.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {

    public static final String VEHICLE_COLOR = "Black";
    public static final String VEHICLE_NUMBER = "KA-09-FN-1234";
    private Vehicle vehicle;

    @Before
    public void setUp(){
        Vehicle vehicle = new Vehicle();
        vehicle.setColor(VEHICLE_COLOR);
        vehicle.setRegNumber(VEHICLE_NUMBER);
    }

    @Test
    public void getRegNumber() throws Exception {
        assertEquals(VEHICLE_NUMBER, vehicle.getRegNumber());
    }


    @Test
    public void getColor() throws Exception {
        assertEquals(VEHICLE_COLOR, vehicle.getColor() );
    }

}