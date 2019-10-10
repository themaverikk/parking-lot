package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotColorInfoDao;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;
import com.gojek.parkinglot.utils.VehicleValidationUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParkingSlotColorInfoDaoImpl implements ParkingSlotColorInfoDao {

    private Map<String, Set<Integer>> colorToSlotNumbersMap;
    private Map<String, Set<String>> colorToRegNumberMap;

    private static final ParkingSlotColorInfoDao instance = new ParkingSlotColorInfoDaoImpl();

    // package default constructor so that it can be instantiated in unit tests, for actual use we'll be using singleton instance only
    ParkingSlotColorInfoDaoImpl() {
    }

    public static ParkingSlotColorInfoDao getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlotColorInfo() {
        this.colorToSlotNumbersMap = new HashMap<>();
        this.colorToRegNumberMap = new HashMap<>();
    }

    @Override
    public boolean parkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);

        if (!this.colorToSlotNumbersMap.containsKey(vehicle.getColor())) {
            this.colorToSlotNumbersMap.put(vehicle.getColor(), new HashSet<>());
        }

        if (!this.colorToRegNumberMap.containsKey(vehicle.getColor())) {
            this.colorToRegNumberMap.put(vehicle.getColor(), new HashSet<>());
        }

        return this.colorToSlotNumbersMap.get(vehicle.getColor()).add(slotNumber)
                && this.colorToRegNumberMap.get(vehicle.getColor()).add(vehicle.getRegNumber());
    }

    @Override
    public boolean unParkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);
        validateVehicleColorPresent(vehicle.getColor());

        return this.colorToSlotNumbersMap.get(vehicle.getColor()).remove(slotNumber)
                && this.colorToRegNumberMap.get(vehicle.getColor()).remove(vehicle.getRegNumber());
    }

    @Override
    public List<Integer> getSlotPositionsForVehicleColor(final String vehicleColor) {
        StringUtils.validateNotBlank(vehicleColor);
        validateVehicleColorPresent(vehicleColor);

        final List<Integer> vehicleColorSlots = new ArrayList<>(this.colorToSlotNumbersMap.get(vehicleColor));
        Collections.sort(vehicleColorSlots);

        return vehicleColorSlots;
    }

    @Override
    public List<String> getRegNumbersForVehicleColor(final String vehicleColor) {
        StringUtils.validateNotBlank(vehicleColor);
        validateVehicleColorPresent(vehicleColor);

        return new ArrayList<>(this.colorToRegNumberMap.get(vehicleColor));
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("slotNumber is invalid");
        }
    }

    private void validateVehicleColorPresent(final String vehicleColor) {

        if (isVehicleColorNotInSlotMap(vehicleColor) || isVehicleColorNotInRegNumberMap(vehicleColor)) {
            throw new IllegalArgumentException("No vehicle with given color present in parking slot");
        }
    }

    private boolean isVehicleColorNotInSlotMap(final String vehicleColor) {
        return !this.colorToSlotNumbersMap.containsKey(vehicleColor)
                || this.colorToSlotNumbersMap.get(vehicleColor) == null
                || this.colorToSlotNumbersMap.get(vehicleColor).isEmpty();
    }

    private boolean isVehicleColorNotInRegNumberMap(final String vehicleColor) {
        return !this.colorToRegNumberMap.containsKey(vehicleColor)
                || this.colorToRegNumberMap.get(vehicleColor) == null
                || this.colorToRegNumberMap.get(vehicleColor).isEmpty();
    }

}
