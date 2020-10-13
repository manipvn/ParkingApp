package com.parking.entity;

import com.parking.enums.ParkingSlotType;

import java.time.Instant;

public class ParkingSlot {
    private int slotNumber;
    private ParkingSlotType slotType;
    private Vehicle vehicle;
    private Instant startTime;
    private Instant endTime;
    private boolean available = true;

    public ParkingSlot() {
    }

    public ParkingSlot(int slotNumber, ParkingSlotType slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public ParkingSlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(ParkingSlotType slotType) {
        this.slotType = slotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
