package java.com.gojek.parkinglot.main;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParkingSpaceTest {
    private ParkingSpace parkingSpace;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    @Before
    public void createParkingLot() throws Exception {
        parkingSpace = new ParkingSpace();
        parkingSpace.createParkingLot("2");
    }

    @Test
    public void doPark() throws Exception {
        parkingSpace.doPark("KA123", "White");
        assertEquals("Allocated slot number: 1\n",outContent.toString());

    }

    @Test
    public void doLeave() throws Exception {
    }

    @Test
    public void getParkingSpaceStatus() throws Exception {
    }

    @Test
    public void getRegNosFromColor() throws Exception {
    }

    @Test
    public void getSlotNosFromColor() throws Exception {
    }

    @Test
    public void getSlotNoFromRegNo() throws Exception {
    }

}