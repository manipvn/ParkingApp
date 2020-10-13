package com.parking.service;

import com.parking.entity.ParkingArea;
import com.parking.entity.ParkingHistory;
import com.parking.entity.ParkingSlot;
import com.parking.entity.Vehicle;
import com.parking.enums.VehicleType;

import java.time.Instant;
import java.util.ArrayList;

public interface ParkingService {
    public ParkingArea createParkingArea(ArrayList<ParkingSlot> parkingSlots);
    public ParkingSlot parkVehicle(Vehicle vehicle) throws Exception;
    public ParkingSlot parkVehicle(Vehicle vehicle,int slotNumber) throws Exception;
    public ParkingSlot getParkingSlotByNumber(int slotNumber) ;
    public double checkoutVehicle(ParkingSlot slot);
    public double checkoutVehicle(ParkingSlot slot, Instant customTime);
    public ArrayList<ParkingSlot> getParkingSlots();
    public ParkingSlot getParkingSlotByVehicleType(VehicleType vehicleType);
    public void updateSlot(ParkingSlot slot);
    public ArrayList<ParkingHistory> getParkingHistory();
}
