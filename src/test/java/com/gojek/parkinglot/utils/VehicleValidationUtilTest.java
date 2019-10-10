package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.model.Vehicle;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleValidationUtilTest {
    @Test (expected = IllegalArgumentException.class)
    public void validateVehicle() throws Exception {
        Vehicle vehicle = null;
        VehicleValidationUtil.validateVehicle(vehicle);
        vehicle = new Vehicle();
        VehicleValidationUtil.validateVehicle(vehicle);
    }

}