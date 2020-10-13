package com.parking.entity;

import java.time.Instant;

public class ParkingHistory {
    ParkingSlot slot;
    Instant startTime;
    Instant endTime;
    Vehicle vehicle;
    double chargedAmount;

    public ParkingHistory(ParkingSlot slot, Instant startTime, Instant endTime, Vehicle vehicle, double chargedAmount) {
        this.slot = slot;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicle = vehicle;
        this.chargedAmount = chargedAmount;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public void setSlot(ParkingSlot slot) {
        this.slot = slot;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }
}
