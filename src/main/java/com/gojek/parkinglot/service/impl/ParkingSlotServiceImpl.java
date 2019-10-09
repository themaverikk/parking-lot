package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dao.ParkingSlotAvailabilityDao;
import com.gojek.parkinglot.dao.ParkingSlotColorInfoDao;
import com.gojek.parkinglot.dao.ParkingSlotPositionDao;
import com.gojek.parkinglot.dao.ParkingSlotRegNumberInfoDao;
import com.gojek.parkinglot.dao.impl.ParkingSlotAvailabilityDaoImpl;
import com.gojek.parkinglot.dao.impl.ParkingSlotColorInfoDaoImpl;
import com.gojek.parkinglot.dao.impl.ParkingSlotPositionDaoImpl;
import com.gojek.parkinglot.dao.impl.ParkingSlotRegNumberInfoDaoImpl;
import com.gojek.parkinglot.model.ParkingSlot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.service.ParkingSlotService;
import com.gojek.parkinglot.utils.StringUtils;
import com.gojek.parkinglot.utils.VehicleValidationUtil;

import java.util.List;
import java.util.NoSuchElementException;

public class ParkingSlotServiceImpl implements ParkingSlotService {
    private final ParkingSlotPositionDao parkingSlotPositionDao;
    private final ParkingSlotAvailabilityDao parkingSlotAvailabilityDao;
    private final ParkingSlotRegNumberInfoDao parkingSlotRegNumberInfoDao;
    private final ParkingSlotColorInfoDao parkingSlotColorInfoDao;

    private static final ParkingSlotService instance = new ParkingSlotServiceImpl();

    private ParkingSlotServiceImpl() {
        this.parkingSlotPositionDao = ParkingSlotPositionDaoImpl.getInstance();
        this.parkingSlotAvailabilityDao = ParkingSlotAvailabilityDaoImpl.getInstance();
        this.parkingSlotRegNumberInfoDao = ParkingSlotRegNumberInfoDaoImpl.getInstance();
        this.parkingSlotColorInfoDao = ParkingSlotColorInfoDaoImpl.getInstance();
    }

    public static ParkingSlotService getInstance() {
        return instance;
    }

    @Override
    public void initParkingSlot(final int parkingCapacity) {
        parkingSlotPositionDao.initParkingSlotPositions(parkingCapacity);
        parkingSlotAvailabilityDao.initParkingSlotsAvailability(parkingCapacity);
    }

    @Override
    public int parkVehicle(final Vehicle vehicle) {
        VehicleValidationUtil.validateVehicle(vehicle);

        // if there's no parking slot available
        if (!parkingSlotAvailabilityDao.isParkingSlotAvailable()) {
            return -1;
        }

        try {
            final int availableSlotNumber = parkingSlotAvailabilityDao.getNearestAvailableSlot();
            parkingSlotPositionDao.parkVehicle(availableSlotNumber, vehicle);
            parkingSlotAvailabilityDao.occupyParkingSlot(availableSlotNumber);
            parkingSlotRegNumberInfoDao.parkVehicle(availableSlotNumber, vehicle);
            parkingSlotColorInfoDao.parkVehicle(availableSlotNumber, vehicle);

            return availableSlotNumber;

        } catch (final NoSuchElementException ex) {
            throw new IllegalArgumentException("Parking is already full, can't part new vehicle");
        }
    }

    @Override
    public boolean unParkVehicle(final int slotNumber) {
        validateSlotNumber(slotNumber);

        // if parking is empty, vehicle can't be un-parked
        if (!parkingSlotAvailabilityDao.isParkingEmpty()) {
            return false;
        }

        parkingSlotAvailabilityDao.freeParkingSlot(slotNumber);
        final Vehicle unParkedVehicle = parkingSlotPositionDao.unParkVehicle(slotNumber);
        parkingSlotRegNumberInfoDao.unParkVehicle(unParkedVehicle);
        return false;
    }

    @Override
    public List<Integer> getSlotPositionsForVehicleColor(final String vehicleColor) {
        StringUtils.validateNotBlank(vehicleColor);

        return parkingSlotColorInfoDao.getSlotPositionsForVehicleColor(vehicleColor);
    }

    @Override
    public int getSlotNumberForRegNumber(final String regNumber) {
        StringUtils.validateNotBlank(regNumber);

        return parkingSlotRegNumberInfoDao.getSlotNumberForRegNumber(regNumber);
    }

    @Override
    public List<ParkingSlot> getParkingStatus() {
        return parkingSlotPositionDao.getParkingStatus();
    }

    private void validateSlotNumber(final int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("Invalid slot number");
        }
    }
}
