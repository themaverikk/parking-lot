package com.gojek.parkinglot.main;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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
        parkingSpace.createNewParkingLot("2");
    }

    @Test
    public void doPark() throws Exception {
        parkingSpace.doPark("KA123", "White");
        parkingSpace.doPark("KA124", "White");
        parkingSpace.doPark("KA125", "White");
        assertEquals(
                "Allocated slot number: 1\n" +
                        "Allocated slot number: 2\n" +
                        "Sorry, parking lot is full\n", outContent.toString());
    }

    @Test
    public void doLeave() throws Exception {
        parkingSpace.doPark("KA126", "White");
        parkingSpace.doLeave("1");
        parkingSpace.doLeave("1");
        assertEquals("Allocated slot number: 1\n" +
                "Slot number 1 is free\n" +
                "Not found\n", outContent.toString());
    }

    @Test
    public void getParkingSpaceStatus() throws Exception {
        parkingSpace.doPark("KA127", "White");
        parkingSpace.doPark("KA128", "White");
        parkingSpace.getParkingSpaceStatus();
        assertEquals(
                "Allocated slot number: 1\n" +
                        "Allocated slot number: 2\n" +
                        "Slot No.\tRegistration No\tColour\n" +
                        "1\tKA127\tWhite\n" +
                        "2\tKA128\tWhite\n", outContent.toString());
    }

    @Test
    public void getRegNosFromColor() throws Exception {
        parkingSpace.doPark("KA129", "Black");
        parkingSpace.doPark("KA130", "Black");
        parkingSpace.getRegNosFromColor("Black");
        assertEquals(
                "Allocated slot number: 1\n" +
                        "Allocated slot number: 2\n" +
                        "KA130, KA129\n", outContent.toString());
    }



    @Test
    public void getSlotNoFromRegNo() throws Exception {
        parkingSpace.doPark("KA133", "White");
        parkingSpace.doPark("KA134", "Black");
        parkingSpace.getSlotNoFromRegNo("KA134");
        assertEquals(
                "Allocated slot number: 1\n" +
                        "Allocated slot number: 2\n" +
                        "2\n", outContent.toString());

    }

    @Test
    public void getSlotNosFromColor() throws Exception {
        parkingSpace.doPark("KA131", "White");
        parkingSpace.doPark("KA132", "Black");

        parkingSpace.getSlotNosFromColor("White");
        assertEquals(
                "Allocated slot number: 1\n" +
                        "Allocated slot number: 2\n" +
                        "1\n", outContent.toString());
    }



}