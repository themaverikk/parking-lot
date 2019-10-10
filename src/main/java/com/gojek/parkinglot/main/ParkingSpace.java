package com.gojek.parkinglot.main;

import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingSlotService;
import com.gojek.parkinglot.service.impl.ParkingSlotServiceImpl;

import java.util.List;

public class ParkingSpace {

    private ParkingSlotService parkingSlotService;

    public void createParkingLot(final String lotNumber){
        parkingSlotService = ParkingSlotServiceImpl.getInstance();
        parkingSlotService.initParkingSlot(Integer.parseInt(lotNumber));

        System.out.println(String.format("Created a parking lot with %s slots", lotNumber));
    }

    public void createNewParkingLot(final String lotNumber){
        parkingSlotService = ParkingSlotServiceImpl.getNewInstance();
        parkingSlotService.initParkingSlot(Integer.parseInt(lotNumber));

    }

    public void doPark(final String regNo, final String color){

        final int slotNumber = parkingSlotService.parkVehicle(new Vehicle(regNo, color));
        if(slotNumber == -1){
            System.out.println("Sorry, parking lot is full");
        }else{
            System.out.println("Allocated slot number: " + slotNumber);
        }

    }

    public void doLeave(final String slotNumber){
        try{
            if(parkingSlotService.unParkVehicle(Integer.parseInt(slotNumber)))
                System.out.println("Slot number "+slotNumber+" is free");
            else
                System.out.println("Not found");

        } catch (final IllegalArgumentException e) {
            System.out.println("Not found");
        }

    }

    public void getParkingSpaceStatus(){
        final List<ParkingSlot> parkingSlots = parkingSlotService.getParkingStatus();
        System.out.println("Slot No.\tRegistration No\tColour");
        for (final ParkingSlot parkingSlot: parkingSlots) {
            System.out.println(parkingSlot.getSlotNumber() + "\t\t" + parkingSlot.getParkedVehicle().getRegNumber()
            + "\t" + parkingSlot.getParkedVehicle().getColor());
        }

    }

    public void getRegNosFromColor(final String color){
        final List<String> regNos = parkingSlotService.getRegNumbersForVehicleColor(color);

        for(int i=0;i<regNos.size()-1;i++){
            System.out.print(regNos.get(i) + ", ");
        }
        System.out.println(regNos.get(regNos.size()-1));

    }

    public void getSlotNosFromColor(final String color){
       final List<Integer> slots = parkingSlotService.getSlotPositionsForVehicleColor(color);

       for(int i=0;i<slots.size()-1;i++){
           System.out.print(slots.get(i) + ", ");
       }
        System.out.println(slots.get(slots.size()-1));
    }

    public void getSlotNoFromRegNo(final String regNo){
        try{
            final int slotNumber = parkingSlotService.getSlotNumberForRegNumber(regNo);
            System.out.println(slotNumber);
        }catch (final IllegalArgumentException e){
            System.out.println("Not found");
        }
    }
}
