package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.ParkingSlotRegNumberInfoDao;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.utils.StringUtils;
import com.gojek.parkinglot.utils.VehicleValidationUtil;

import java.util.HashMap;
import java.util.Map;

public class ParkingSlotRegNumberInfoDaoImpl implements ParkingSlotRegNumberInfoDao {
    private static final ParkingSlotRegNumberInfoDaoImpl instance = new ParkingSlotRegNumberInfoDaoImpl();

    private Map<String, Integer> regNumberToSlotNumberMap;

    // package default constructor so that it can be instantiated in unit tests, for actual use we'll be using singleton instance only
    ParkingSlotRegNumberInfoDaoImpl() {
    }

    public static ParkingSlotRegNumberInfoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlotRegNumberInfo() {
        this.regNumberToSlotNumberMap = new HashMap<>();
    }

    @Override
    public void parkVehicle(final int slotNumber, final Vehicle vehicle) {
        verifyInitialization();
        validateSlotNumber(slotNumber);
        VehicleValidationUtil.validateVehicle(vehicle);
        validateVehicleIsNotPresent(vehicle);

        this.regNumberToSlotNumberMap.put(vehicle.getRegNumber(), slotNumber);
    }

    @Override
    public void unParkVehicle(final Vehicle vehicle) {
        verifyInitialization();
        VehicleValidationUtil.validateVehicle(vehicle);
        validateVehiclePresent(vehicle);

        this.regNumberToSlotNumberMap.remove(vehicle.getRegNumber());
    }

    @Override
    public int getSlotNumberForRegNumber(final String regNumber) {
        verifyInitialization();
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

    private void verifyInitialization() {
        if (this.regNumberToSlotNumberMap == null) {
            throw new IllegalStateException("Parking lot has not been initialized properly");
        }
    }
}
