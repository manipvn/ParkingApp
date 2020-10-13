package com.parking.service;

import com.parking.entity.Vehicle;
import com.parking.enums.VehicleType;

import java.util.List;

public interface VehicleService {
    public Vehicle addVehicle(String vehicleNumber, VehicleType vehicleType);
}
