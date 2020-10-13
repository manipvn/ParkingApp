package com.parking.entity;

import com.parking.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private List<ParkingHistory> history = new ArrayList<>();
    private ParkingSlot slot;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<ParkingHistory> getHistory() {
        return history;
    }

    public void setHistory(List<ParkingHistory> history) {
        this.history = history;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }
}
