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

    private final Map<String, Set<Integer>> colorToSlotNumbersMap;

    private static final ParkingSlotColorInfoDao instance = new ParkingSlotColorInfoDaoImpl();

    private ParkingSlotColorInfoDaoImpl() {
        colorToSlotNumbersMap = new HashMap<>();
    }

    public static ParkingSlotColorInfoDao getInstance() {
        return instance;
    }

    @Override
    public boolean parkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);

        if (!this.colorToSlotNumbersMap.containsKey(vehicle.getColor())) {
            this.colorToSlotNumbersMap.put(vehicle.getColor(), new HashSet<>());
        }

        return this.colorToSlotNumbersMap.get(vehicle.getColor()).add(slotNumber);
    }

    @Override
    public boolean unParkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);
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

    private void validateVehicleColorPresent(final String vehicleColor) {
        if (!this.colorToSlotNumbersMap.containsKey(vehicleColor)
                || this.colorToSlotNumbersMap.get(vehicleColor) == null
                || this.colorToSlotNumbersMap.get(vehicleColor).isEmpty()) {

            throw new IllegalArgumentException("No vehicle with given color present in parking slot");
        }
    }
}
