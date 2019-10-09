package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.RegNumberSlotInfoDao;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RegNumberSlotInfoDaoImpl implements RegNumberSlotInfoDao {
    private static final RegNumberSlotInfoDao instance = new RegNumberSlotInfoDaoImpl();

    private final Map<String, Integer> regNumberToSlotNumberMap;

    private RegNumberSlotInfoDaoImpl() {
        regNumberToSlotNumberMap = new HashMap<>();
    }

    public static RegNumberSlotInfoDao getInstance() {
        return instance;
    }

    @Override
    public void parkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        validateVehicle(vehicle);
        validateVehicleIsNotPresent(vehicle);

        this.regNumberToSlotNumberMap.put(vehicle.getRegNumber(), slotNumber);
    }

    @Override
    public void unParkVehicle(final Vehicle vehicle) {
        validateVehicle(vehicle);
        validateVehiclePresent(vehicle);

        this.regNumberToSlotNumberMap.remove(vehicle.getRegNumber());
    }

    @Override
    public int getSlotNumberForRegNumber(final String regNumber) {
        StringUtils.validateNotBlank(regNumber);
        validateRegNumberPresent(regNumber);

        return this.regNumberToSlotNumberMap.get(regNumber);
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("Invalid slot number");
        }
    }

    private void validateVehicle(final Vehicle vehicle) {
        if (vehicle == null || StringUtils.isBlank(vehicle.getRegNumber()) || StringUtils.isBlank(vehicle.getColor())) {
            throw new IllegalArgumentException("Invalid vehicle object");
        }
    }

    private void validateVehicleIsNotPresent(final Vehicle vehicle) {
        if (regNumberToSlotNumberMap.containsKey(vehicle.getRegNumber())) {
            throw new IllegalArgumentException("given slot is not empty");
        }
    }

    private void validateVehiclePresent(final Vehicle vehicle) {
        if (!regNumberToSlotNumberMap.containsKey(vehicle.getRegNumber())) {
            throw new IllegalArgumentException("vehicle is not present in parking lot");
        }
    }

    private void validateRegNumberPresent(final String regNumber) {
        if (!regNumberToSlotNumberMap.containsKey(regNumber)) {
            throw new IllegalArgumentException("vehicle is not present in parking lot");
        }
    }
}
