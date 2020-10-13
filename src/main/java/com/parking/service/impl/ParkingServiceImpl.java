package com.parking.service.impl;

import com.parking.entity.ParkingArea;
import com.parking.entity.ParkingHistory;
import com.parking.entity.ParkingSlot;
import com.parking.entity.Vehicle;
import com.parking.enums.VehicleType;
import com.parking.exception.ParkingFullException;
import com.parking.service.ParkingService;
import com.parking.utils.ParkingCalculation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    ParkingArea parkingArea = new ParkingArea();
    ArrayList<ParkingHistory> parkingHistories = new ArrayList<>();

    @Override
    public ParkingArea createParkingArea(ArrayList<ParkingSlot> parkingSlots) {
        this.parkingArea.setSlots(parkingSlots);
        this.parkingArea.setSlotCount(parkingSlots.size());
        return parkingArea;
    }

    @Override
    public synchronized ParkingSlot parkVehicle(Vehicle vehicle) throws Exception{
        try{
            ParkingSlot slot = getParkingSlotByVehicleType(vehicle.getVehicleType());
            slot.setVehicle(vehicle);
            slot.setStartTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
            slot.setAvailable(false);
            vehicle.setSlot(slot);
            this.updateSlot(slot);
            return slot;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;

    }

    @Override
    public synchronized ParkingSlot parkVehicle(Vehicle vehicle, int slotNumber) throws Exception {
            ParkingSlot slot = getParkingSlotByNumber(slotNumber);
            if(slot!=null){
                slot.setVehicle(vehicle);
                slot.setStartTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
                slot.setAvailable(false);
                vehicle.setSlot(slot);
                this.updateSlot(slot);
                return slot;
            }else{
                throw new ParkingFullException("Parking is Full");
            }
    }

    @Override
    public double checkoutVehicle(ParkingSlot slot) {
        double calculatedAmount = ParkingCalculation.usageCalculation(slot.getStartTime(),Instant.now(),slot.getSlotType());
        this.updateParkingHistory(slot,slot.getStartTime(),LocalDateTime.now().toInstant(ZoneOffset.UTC),slot.getVehicle(),calculatedAmount);
        slot.setAvailable(true);
        slot.setVehicle(null);
        return calculatedAmount;
    }

    private void updateParkingHistory(ParkingSlot slot, Instant startTime, Instant endTime, Vehicle vehicle, double chargedAmount) {
        ParkingHistory parkingHistory = new ParkingHistory(slot,startTime,endTime,vehicle,chargedAmount);
        List<ParkingHistory> histories = vehicle.getHistory();
        histories.add(parkingHistory);
        vehicle.setHistory(histories);
        parkingHistories.add(parkingHistory);
    }

    @Override
    public double checkoutVehicle(ParkingSlot slot, Instant customTime) {
        ArrayList<ParkingSlot> parkingSlots = this.getParkingSlots();
        double calculatedAmount = ParkingCalculation.usageCalculation(slot.getStartTime(),customTime,slot.getSlotType());
        slot.setAvailable(true);
        this.updateParkingHistory(slot,slot.getStartTime(),customTime,slot.getVehicle(),calculatedAmount);
        return calculatedAmount;
    }

    @Override
    public ArrayList<ParkingSlot> getParkingSlots() {
        return this.parkingArea.getSlots();
    }

    @Override
    public synchronized ParkingSlot getParkingSlotByVehicleType(VehicleType vehicleType) {
        for (ParkingSlot slot : getParkingSlots()) {
            if(slot.getSlotType().toString().equals(vehicleType.toString()) && slot.isAvailable()){
                return slot;
            }
        }
        return null;
    }

    @Override
    public synchronized ParkingSlot getParkingSlotByNumber(int slotNumber) {
        for (ParkingSlot slot : getParkingSlots()) {
            if(slot.getSlotNumber() == slotNumber && slot.isAvailable()){
                return slot;
            }
        }
        return null;
    }

    @Override
    public void updateSlot(ParkingSlot slot) {
        ArrayList<ParkingSlot> slots = this.parkingArea.getSlots();
        slots.set(slot.getSlotNumber()-1,slot);
        this.parkingArea.setSlots(slots);
    }

    @Override
    public ArrayList<ParkingHistory> getParkingHistory() {
        return this.parkingHistories;
    }
}
