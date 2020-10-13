package com.parking.entity;

import com.parking.enums.VehicleType;

public class HatchBack extends Vehicle{
    public HatchBack(String vehicleNumber) {
        this.setVehicleNumber(vehicleNumber);
        this.setVehicleType(VehicleType.HATCHBACK);
    }
}
