package com.parking.utils;

import com.parking.enums.ParkingSlotType;

import java.time.Duration;
import java.time.Instant;

public class ParkingCalculation {
    public static double usageCalculation(Instant startTime, Instant endTime, ParkingSlotType slotType){
        double charges = 0;
        switch (slotType){
            case SUV:
                charges = getCharges(startTime, endTime,ParkingConstants.suvExtraHours,ParkingConstants.suvMinimumCharges);
                break;
            case HATCHBACK:
                charges = getCharges(startTime, endTime,ParkingConstants.hatchBackExtraHours,ParkingConstants.hatchBackMinimumCharge);
                break;
            case TWO_WHEELER:
            default:
                charges = getCharges(startTime, endTime,ParkingConstants.twoWheelerExtraHours,ParkingConstants.twoWheelerMinimumCharge);
        }
        return charges;
    }

    private static double getCharges(Instant startTime, Instant endTime,int extraCharges,int minimalCharges) {
        double charges = minimalCharges;
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        System.out.println("Duration Hours"+hours);
        if(hours>2 && hours<=4){
            charges = minimalCharges + extraCharges;
        }else if(hours>4){
            charges = minimalCharges + extraCharges + (hours-4)*ParkingConstants.defaultHourly;
        }
        return charges;

    }
}
