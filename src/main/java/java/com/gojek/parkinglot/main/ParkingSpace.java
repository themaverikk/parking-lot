package java.com.gojek.parkinglot.main;

import java.com.gojek.parkinglot.model.ParkingSlot;
import java.com.gojek.parkinglot.model.Vehicle;
import java.com.gojek.parkinglot.service.ParkingSlotService;
import java.com.gojek.parkinglot.service.impl.ParkingSlotServiceImpl;

import java.util.List;

public class ParkingSpace {

    private ParkingSlotService parkingSlotService;

    public void createParkingLot(final String lotNumber){
        parkingSlotService = ParkingSlotServiceImpl.getInstance();
        parkingSlotService.initParkingSlot(Integer.parseInt(lotNumber));
    }

    public void doPark(final String regNo, final String color){

        int slotNumber = parkingSlotService.parkVehicle(new Vehicle(regNo, color));
        if(slotNumber == -1){
            System.out.println("Sorry, parking lot is full");
        }else{
            System.out.println("Allocated slot number: " + slotNumber);
        }

    }

    public void doLeave(final String slotNumber){
        if(parkingSlotService.unParkVehicle(Integer.parseInt(slotNumber))){
            System.out.println("Slot number "+slotNumber+" is free");
        }else{
            System.out.println("Not found");
        }

    }

    public void getParkingSpaceStatus(){
        List<ParkingSlot> parkingSlots = parkingSlotService.getParkingStatus();
        System.out.println("Slot No.\tRegistration No\tColour");
        for (ParkingSlot parkingSlot: parkingSlots) {
            System.out.println(parkingSlot.getSlotNumber() + "\t" + parkingSlot.getParkedVehicle().getRegNumber()
            + "\t" + parkingSlot.getParkedVehicle().getColor());
        }

    }

    public void getRegNosFromColor(final String color){
        //parkingSlotService.get

    }

    public void getSlotNosFromColor(final String color){
       List<Integer> slots = parkingSlotService.getSlotPositionsForVehicleColor(color);

       for(int i=0;i<slots.size()-1;i++){
           System.out.println(slots.get(i) + ", ");
       }
        System.out.println(slots.get(slots.size()-1));
    }

    public void getSlotNoFromRegNo(final String regNo){
        try{
            int slotNumber = parkingSlotService.getSlotNumberForRegNumber(regNo);
            System.out.println(slotNumber);
        }catch (IllegalArgumentException e){
            System.out.println("Not found");
        }
    }


}