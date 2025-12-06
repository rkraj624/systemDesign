package dsa.practice.lld.parkingLot;

import dsa.practice.lld.parkingLot.gate.EntryGate;
import dsa.practice.lld.parkingLot.gate.ExitGate;
import dsa.practice.lld.parkingLot.strategy.ParkingFeeCalculatorStrategy;
import dsa.practice.lld.parkingLot.strategy.ParkingSpotAssignmentStrategy;

import java.util.*;

public class ParkingLot {
    private final String id;
    private List<ParkingFloor> floors = new ArrayList<>();
    private List<EntryGate> entryGates = new ArrayList<>();
    private List<ExitGate> exitGates = new ArrayList<>();

    private ParkingSpotAssignmentStrategy spotAssignmentStrategy;
    private ParkingFeeCalculatorStrategy feeCalculatorStrategy;

    private Map<String, Ticket> activeTickets = new HashMap<>();

    private static ParkingLot INSTANCE;

    private ParkingLot(String id) {
        this.id = id;
    }

    public static synchronized ParkingLot getInstance(String id) {
        if (INSTANCE == null) {
            INSTANCE = new ParkingLot(id);
        }
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public void addEntryGate(EntryGate gate) {
        entryGates.add(gate);
    }

    public void addExitGate(ExitGate gate) {
        exitGates.add(gate);
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public ParkingSpotAssignmentStrategy getSpotAssignmentStrategy() {
        return spotAssignmentStrategy;
    }

    public void setSpotAssignmentStrategy(ParkingSpotAssignmentStrategy strategy) {
        this.spotAssignmentStrategy = strategy;
    }

    public ParkingFeeCalculatorStrategy getFeeCalculatorStrategy() {
        return feeCalculatorStrategy;
    }

    public void setFeeCalculatorStrategy(ParkingFeeCalculatorStrategy strategy) {
        this.feeCalculatorStrategy = strategy;
    }

    public void addActiveTicket(Ticket ticket) {
        activeTickets.put(ticket.getId(), ticket);
    }

    public Ticket getActiveTicket(String ticketId) {
        return activeTickets.get(ticketId);
    }

    public void removeActiveTicket(String ticketId) {
        activeTickets.remove(ticketId);
    }
}
