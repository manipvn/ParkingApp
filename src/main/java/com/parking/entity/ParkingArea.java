package com.parking.entity;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;


public class ParkingArea {


    private int id;

    private ArrayList<ParkingSlot> slots = new ArrayList<>();
    private Address address;
    private HashMap<Integer,ArrayList<ParkingHistory>> parkingHistory;
    private int slotCount;

    public int getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    public ArrayList<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<ParkingSlot> slots) {
        this.slots = slots;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HashMap<Integer, ArrayList<ParkingHistory>> getParkingHistory() {
        return parkingHistory;
    }

    public void setParkingHistory(HashMap<Integer, ArrayList<ParkingHistory>> parkingHistory) {
        this.parkingHistory = parkingHistory;
    }
}
