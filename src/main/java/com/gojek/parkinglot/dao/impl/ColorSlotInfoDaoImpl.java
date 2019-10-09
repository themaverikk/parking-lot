package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ColorSlotInfoDao;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ColorSlotInfoDaoImpl implements ColorSlotInfoDao {

    private final Map<String, Set<Integer>> colorToSlotNumbersMap;

    private static final ColorSlotInfoDao instance = new ColorSlotInfoDaoImpl();

    private ColorSlotInfoDaoImpl() {
        colorToSlotNumbersMap = new HashMap<>();
    }

    public static ColorSlotInfoDao getInstance() {
        return instance;
    }

    @Override
    public boolean parkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        validateVehicle(vehicle);

        if (!this.colorToSlotNumbersMap.containsKey(vehicle.getColor())) {
            this.colorToSlotNumbersMap.put(vehicle.getColor(), new HashSet<>());
        }

        return this.colorToSlotNumbersMap.get(vehicle.getColor()).add(slotNumber);
    }

    @Override
    public boolean unParkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        validateVehicle(vehicle);
        validateVehicleColorPresent(vehicle.getColor());

        return this.colorToSlotNumbersMap.get(vehicle.getColor()).remove(slotNumber);
    }

    @Override
    public List<Integer> getSlotPositionsForVehicleColor(final String vehicleColor) {
        StringUtils.validateNotBlank(vehicleColor);
        validateVehicleColorPresent(vehicleColor);

        final List<Integer> vehicleColorSlots = new ArrayList<>(this.colorToSlotNumbersMap.get(vehicleColor));
        Collections.sort(vehicleColorSlots);

        return vehicleColorSlots;
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("slotNumber is invalid");
        }
    }

    private void validateVehicle(final Vehicle vehicle) {
        if (vehicle == null || StringUtils.isBlank(vehicle.getRegNumber()) || StringUtils.isBlank(vehicle.getColor())) {
            throw new IllegalArgumentException("Invalid vehicle object");
        }
    }

    private void validateVehicleColorPresent(final String vehicleColor) {
        if (!this.colorToSlotNumbersMap.containsKey(vehicleColor)
                || this.colorToSlotNumbersMap.get(vehicleColor) == null
                || this.colorToSlotNumbersMap.get(vehicleColor).isEmpty()) {

            throw new IllegalArgumentException("No vehicle with given color present in parking slot");
        }
    }
}
