package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotRegNumberInfoDao;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;
import com.gojek.parkinglot.utils.VehicleValidationUtil;

import java.util.HashMap;
import java.util.Map;

public class ParkingSlotRegNumberInfoDaoImpl implements ParkingSlotRegNumberInfoDao {
    private static final ParkingSlotRegNumberInfoDao instance = new ParkingSlotRegNumberInfoDaoImpl();

    private final Map<String, Integer> regNumberToSlotNumberMap;

    private ParkingSlotRegNumberInfoDaoImpl() {
        regNumberToSlotNumberMap = new HashMap<>();
    }

    public static ParkingSlotRegNumberInfoDao getInstance() {
        return instance;
    }

    @Override
    public void parkVehicle(final int slotNumber, final Vehicle vehicle) {
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);
        validateVehicleIsNotPresent(vehicle);

        this.regNumberToSlotNumberMap.put(vehicle.getRegNumber(), slotNumber);
    }

    @Override
    public void unParkVehicle(final Vehicle vehicle) {
        VehicleValidationUtil.validateVehicle(vehicle);
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
