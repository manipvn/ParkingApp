package com.parking.entity;

import com.parking.enums.VehicleType;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(String vehicleNumber) {
        this.setVehicleNumber(vehicleNumber);
        this.setVehicleType(VehicleType.TWO_WHEELER);
    }
}
