package com.parking.entity;

import com.parking.enums.VehicleType;

public class SUV extends Vehicle{
    public SUV(String vehicleNumber) {
        this.setVehicleNumber(vehicleNumber);
        this.setVehicleType(VehicleType.SUV);
    }
}
