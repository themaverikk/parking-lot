package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.model.Vehicle;

public final class VehicleValidationUtil {
    private VehicleValidationUtil() {
    }

    public static void validateVehicle(final Vehicle vehicle) {
        if (vehicle == null || StringUtils.isBlank(vehicle.getRegNumber()) || StringUtils.isBlank(vehicle.getColor())) {
            throw new IllegalArgumentException("Invalid vehicle object");
        }
    }
}
