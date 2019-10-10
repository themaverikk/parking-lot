package com.gojek.parkinglot.model;

import com.gojek.parkinglot.utils.StringUtils;

public class Vehicle {
    private String regNumber;
    private String color;

    public Vehicle(){}

    public  Vehicle(final String regNumber, final String color){
        this.regNumber = regNumber;
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(final String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        final Vehicle vehicle = (Vehicle) o;

        return StringUtils.equals(this.getRegNumber(), vehicle.getRegNumber()) && StringUtils.equals(this.getColor(), vehicle.getColor());
    }

    @Override
    public int hashCode() {
        final int regNumberHashCode = this.getRegNumber() == null ? 0 : this.getRegNumber().hashCode();
        final int colorHashCode = this.getColor() == null ? 0 : this.getColor().hashCode();

        return 31 * regNumberHashCode + colorHashCode;
    }
}
