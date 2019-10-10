package com.gojek.parkinglot.model;

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
}
