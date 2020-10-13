package com.parking;


import com.parking.entity.ParkingHistory;
import com.parking.entity.ParkingSlot;
import com.parking.entity.Vehicle;
import com.parking.enums.ParkingSlotType;
import com.parking.enums.VehicleType;
import com.parking.service.ParkingService;
import com.parking.service.VehicleService;
import com.parking.service.impl.ParkingServiceImpl;
import com.parking.service.impl.VehicleServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

public class ParkingAppApplication {

	public static void main(String[] args) {
		System.out.println("Parking App");

		ParkingService parkingService = new ParkingServiceImpl();
		VehicleService vehicleService = new VehicleServiceImpl();
		parkingFeedData(parkingService);

		Vehicle vehicle1 = vehicleService.addVehicle("KA05 KA 1231", VehicleType.TWO_WHEELER);
		Vehicle vehicle2 = vehicleService.addVehicle("KA05 KA 1232", VehicleType.TWO_WHEELER);
		Vehicle vehicle3 = vehicleService.addVehicle("KA05 KA 1233", VehicleType.TWO_WHEELER);
		Vehicle vehicle4 = vehicleService.addVehicle("KA05 KA 1234", VehicleType.HATCHBACK);
		Vehicle vehicle7 = vehicleService.addVehicle("KA05 KA 1237", VehicleType.HATCHBACK);
		Vehicle vehicle8 = vehicleService.addVehicle("KA05 KA 1238", VehicleType.SUV);
		Vehicle vehicle9 = vehicleService.addVehicle("KA05 KA 1239", VehicleType.SUV);

		ParkingSlot slot1 = new ParkingSlot();
		ParkingSlot slot2 = new ParkingSlot();
		ParkingSlot exceptionSlot = new ParkingSlot();
		try{
			slot1 = parkingService.parkVehicle(vehicle1);
			slot2 = parkingService.parkVehicle(vehicle2);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		try{
			exceptionSlot = parkingService.parkVehicle(vehicle2,1);
		}catch (Exception e){
			e.printStackTrace();
		}
		if(slot1!=null){
			LocalDateTime localDateTime = LocalDateTime.of(2020, Month.OCTOBER,12,23,20);
			System.out.println("Charged Amount"+parkingService.checkoutVehicle(slot1,localDateTime.toInstant(ZoneOffset.UTC)));
		}
		if(slot2!=null){
			LocalDateTime localDateTime = LocalDateTime.of(2020, Month.OCTOBER,12,23,20);
			System.out.println("Charged Amount"+parkingService.checkoutVehicle(slot2,localDateTime.toInstant(ZoneOffset.UTC)));
		}

		try{
			slot1 = parkingService.parkVehicle(vehicle1,1);
		}catch(Exception exception){
			exception.printStackTrace();
		}
		if(slot1!=null){
			LocalDateTime localDateTime = LocalDateTime.of(2020, Month.OCTOBER,12,23,20);
			System.out.println("Charged Amount"+parkingService.checkoutVehicle(slot1,localDateTime.toInstant(ZoneOffset.UTC)));
		}


		List<ParkingHistory> histories = vehicle1.getHistory();
		for (ParkingHistory history: histories) {
			System.out.println(history.getSlot().getSlotNumber());
			System.out.println(history.getVehicle().getVehicleNumber());
		}

		 histories = vehicle2.getHistory();
		for (ParkingHistory history: histories) {
			System.out.println(history.getSlot().getSlotNumber());
			System.out.println(history.getVehicle().getVehicleNumber());
		}


		displayParkingHistory(parkingService);

	}

	private static void displayParkingHistory(ParkingService parkingService) {
		for (ParkingHistory history : parkingService.getParkingHistory()) {
			StringJoiner joiner = new StringJoiner(",", "[","]")
					.add(" "+history.getVehicle().getVehicleNumber())
					.add(" "+history.getSlot().getSlotNumber())
					.add(" "+history.getSlot().getSlotType().toString())
					.add(" "+history.getStartTime())
					.add(" "+history.getEndTime());
			System.out.println(joiner.toString());
		}
	}

	private static void parkingFeedData(ParkingService parkingService) {
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(new FileReader("/Users/pkalluru/Documents/udaan/ParkingApp/src/main/resources/slots.json"));
			JSONArray jsonArray = (JSONArray) obj;
			ArrayList<ParkingSlot> slots = new ArrayList<>();
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while (iterator.hasNext()){
				JSONObject slotObject = iterator.next();
				int slotNumber = Integer.valueOf((String)slotObject.get("slot"));
				ParkingSlot slot = new ParkingSlot(slotNumber,
						ParkingSlotType.valueOf((String) slotObject.get("type")));
				slots.add(slot);
			}
			parkingService.createParkingArea(slots);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
