package java.com.gojek.parkinglot.main;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Commands {
    public Map<String, Method> commandsMap;

    public Commands() {
        commandsMap = new HashMap<String, Method>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("create_parking_lot", ParkingSpace.class.getMethod("createParkingLot", String.class));
        commandsMap.put("park", ParkingSpace.class.getMethod("doPark", String.class, String.class));
        commandsMap.put("leave", ParkingSpace.class.getMethod("doLeave", String.class));
        commandsMap.put("status", ParkingSpace.class.getMethod("getParkingSpaceStatus"));
        commandsMap.put("registration_numbers_for_cars_with_colour", ParkingSpace.class.getMethod("getRegNosFromColor", String.class));
        commandsMap.put("slot_numbers_for_cars_with_colour", ParkingSpace.class.getMethod("getSlotNosFromColor", String.class));
        commandsMap.put("slot_number_for_registration_number", ParkingSpace.class.getMethod("getSlotNoFromRegNo", String.class));
    }
}
