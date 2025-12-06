package dsa.practice.lld.parkingLot.gate;

import dsa.practice.lld.parkingLot.ParkingLot;
import dsa.practice.lld.parkingLot.Ticket;

import java.time.Instant;

public class ExitGate extends Gate {

    public ExitGate(String id, ParkingLot parkingLot) {
        super(id, parkingLot);
    }

    public double exit(String ticketId) {
        Ticket ticket = parkingLot.getActiveTicket(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid TicketBuilderDesign");
        }

        Instant exitTime = Instant.now();
        double amount = parkingLot.getFeeCalculatorStrategy()
                .calculateFee(ticket.getEntryTime(), exitTime, ticket.getVehicle().getType());

        ticket.close(exitTime, amount);
        ticket.getSpot().unpark();
        parkingLot.removeActiveTicket(ticketId);

        // here we could also create a Payment object, record transaction, etc.
        return amount;
    }
}
