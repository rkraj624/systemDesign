package dsa.practice.lld.parkingLot.gate;

import dsa.practice.lld.parkingLot.ParkingLot;
import dsa.practice.lld.parkingLot.ParkingSpot;
import dsa.practice.lld.parkingLot.Ticket;
import dsa.practice.lld.parkingLot.vehicle.Vehicle;

import java.time.Instant;
import java.util.UUID;

public class EntryGate extends Gate {

    public EntryGate(String id, ParkingLot parkingLot) {
        super(id, parkingLot);
    }

    public Ticket enter(Vehicle vehicle) {
        ParkingSpot spot = parkingLot.getSpotAssignmentStrategy()
                .assignSpot(vehicle.getType(), parkingLot);

        if (spot == null) {
            throw new RuntimeException("No free spot available for vehicle type: " + vehicle.getType());
        }

        spot.park(vehicle);
        String ticketId = UUID.randomUUID().toString();

        Ticket ticket = new Ticket.Builder().setId(ticketId)
                .setVehicle(vehicle)
                .setEntryTime(Instant.now())
                .setSpot(spot)
                .build();

        parkingLot.addActiveTicket(ticket);
        return ticket;
    }
}
