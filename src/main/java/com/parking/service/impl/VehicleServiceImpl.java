package com.parking.service.impl;

import com.parking.entity.HatchBack;
import com.parking.entity.SUV;
import com.parking.entity.TwoWheeler;
import com.parking.entity.Vehicle;
import com.parking.enums.VehicleType;
import com.parking.service.VehicleService;

import java.util.ArrayList;

public class VehicleServiceImpl implements VehicleService {

    public ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Vehicle addVehicle(String vehicleNumber, VehicleType vehicleType) {
        Vehicle vehicle = generateVehicle(vehicleNumber,vehicleType);
        vehicles.add(vehicle);
        return vehicle;
    }

    private Vehicle generateVehicle(String vehicleNumber, VehicleType vehicleType) {
        Vehicle vehicle;
        switch (vehicleType){
            case TWO_WHEELER:
                vehicle = new TwoWheeler(vehicleNumber);
                break;
            case SUV:
                vehicle = new SUV(vehicleNumber);
                break;
            case HATCHBACK:
            default:
                vehicle = new HatchBack(vehicleNumber);
        }
        return vehicle;
    }
}
