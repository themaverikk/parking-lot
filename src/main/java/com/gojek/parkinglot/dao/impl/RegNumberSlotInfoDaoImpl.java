package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.dao.RegNumberSlotInfoDao;
import com.gojek.parkinglot.model.Vehicle;

public class RegNumberSlotInfoDaoImpl implements RegNumberSlotInfoDao {
    private static final RegNumberSlotInfoDao instance = new RegNumberSlotInfoDaoImpl();

    private RegNumberSlotInfoDaoImpl() {
    }

    public static RegNumberSlotInfoDao getInstance() {
        return instance;
    }

    @Override
    public int parkVehicle(final int slotNumber, final Vehicle vehicle) {
        return 0;
    }

    @Override
    public boolean unParkVehicle(final int slotNumber, final Vehicle vehicle) {
        return false;
    }

    @Override
    public int getSlotNumberForRegNumber(final String regNumber) {
        return 0;
    }
}
