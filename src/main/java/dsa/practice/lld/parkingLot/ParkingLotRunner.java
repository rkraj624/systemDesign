package dsa.practice.lld.parkingLot;

import dsa.practice.lld.parkingLot.enums.SpotType;
import dsa.practice.lld.parkingLot.gate.EntryGate;
import dsa.practice.lld.parkingLot.gate.ExitGate;
import dsa.practice.lld.parkingLot.strategy.NearestSpotAssignmentStrategy;
import dsa.practice.lld.parkingLot.strategy.SimpleHourlyFeeCalculator;
import dsa.practice.lld.parkingLot.vehicle.Bike;
import dsa.practice.lld.parkingLot.vehicle.Car;
import dsa.practice.lld.parkingLot.vehicle.Vehicle;


import java.util.*;

public class ParkingLotRunner {

    public static void main(String[] args) {

        // ---------------- SETUP PARKING LOT ----------------
        ParkingLot parkingLot = ParkingLot.getInstance("P1");

        parkingLot.setSpotAssignmentStrategy(new NearestSpotAssignmentStrategy());
        parkingLot.setFeeCalculatorStrategy(new SimpleHourlyFeeCalculator());

        // ---- FLOORS ----
        ParkingFloor floor1 = new ParkingFloor("F1");
        ParkingFloor floor2 = new ParkingFloor("F2");

        // Floor 1 spots
        floor1.addSpot(new ParkingSpot("F1-S1", SpotType.CAR));
        floor1.addSpot(new ParkingSpot("F1-S2", SpotType.CAR));
        floor1.addSpot(new ParkingSpot("F1-S3", SpotType.BIKE));

        // Floor 2 spots
        floor2.addSpot(new ParkingSpot("F2-S1", SpotType.CAR));
        floor2.addSpot(new ParkingSpot("F2-S2", SpotType.TRUCK));
        floor2.addSpot(new ParkingSpot("F2-S3", SpotType.BIKE));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        // ---- GATES ----
        EntryGate entryGate1 = new EntryGate("E1", parkingLot);
        EntryGate entryGate2 = new EntryGate("E2", parkingLot);
        parkingLot.addEntryGate(entryGate1);
        parkingLot.addEntryGate(entryGate2);

        ExitGate exitGate1 = new ExitGate("X1", parkingLot);
        ExitGate exitGate2 = new ExitGate("X2", parkingLot);
        parkingLot.addExitGate(exitGate1);
        parkingLot.addExitGate(exitGate2);

        // ---------------- VEHICLES ----------------
        List<Vehicle> vehicles = Arrays.asList(
                new Car("BR01AB1234"),
                new Bike("BR02CD5678"),
                new Car("BR03EF9101"),
                new Bike("BR04GH2345"),
                new Car("BR05IJ6789")
        );

        Map<Vehicle, Ticket> activeTickets = new LinkedHashMap<>();

        System.out.println("========= VEHICLE ENTRY =========");

        // Alternate entry gates for vehicles
        int idx = 0;
        for (Vehicle v : vehicles) {
            EntryGate chosenGate = (idx % 2 == 0) ? entryGate1 : entryGate2;
            try {
                Ticket ticket = chosenGate.enter(v);
                activeTickets.put(v, ticket);

                System.out.println("Gate: " + chosenGate.getId()
                        + " | Vehicle: " + v.getVehicleNumber()
                        + " | Type: " + v.getType()
                        + " | Spot: " + ticket.getSpot().getId()
                        + " | TicketBuilderDesign: " + ticket.getId());
            } catch (RuntimeException e) {
                System.out.println("Gate: " + chosenGate.getId()
                        + " | Vehicle: " + v.getVehicleNumber()
                        + " | FAILED TO PARK: " + e.getMessage());
            }
            idx++;
        }

        System.out.println("=================================\n");

        // Simulate some time passing (just for demo)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ignore
        }

        System.out.println("========= VEHICLE EXIT =========");

        // Now exit all successfully parked vehicles using exit gates alternatively
        int exitIdx = 0;
        for (Map.Entry<Vehicle, Ticket> entry : activeTickets.entrySet()) {
            Vehicle v = entry.getKey();
            Ticket t = entry.getValue();

            ExitGate chosenExitGate = (exitIdx % 2 == 0) ? exitGate1 : exitGate2;
            double amount = chosenExitGate.exit(t.getId());

            System.out.println("Exit Gate: " + chosenExitGate.getId()
                    + " | Vehicle: " + v.getVehicleNumber()
                    + " | Type: " + v.getType()
                    + " | Spot: " + t.getSpot().getId()
                    + " | Amount: ₹" + amount);

            exitIdx++;
        }

        System.out.println("=================================");
    }
}
