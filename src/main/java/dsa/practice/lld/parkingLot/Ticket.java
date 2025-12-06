package dsa.practice.lld.parkingLot;

import dsa.practice.lld.parkingLot.enums.TicketStatus;
import dsa.practice.lld.parkingLot.vehicle.Vehicle;

import java.time.Instant;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final Instant entryTime;
    private Instant exitTime;
    private double amount;
    private TicketStatus status = TicketStatus.ACTIVE;

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.vehicle = builder.vehicle;
        this.spot = builder.spot;
        this.entryTime = builder.entryTime;
        this.exitTime = builder.exitTime;
        this.amount = builder.amount;
        this.status = builder.status;
    }

    public String getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSpot getSpot() { return spot; }
    public Instant getEntryTime() { return entryTime; }
    public Instant getExitTime() { return exitTime; }
    public double getAmount() { return amount; }
    public TicketStatus getStatus() { return status; }

    public void close(Instant exitTime, double amount) {
        this.exitTime = exitTime;
        this.amount = amount;
        this.status = TicketStatus.PAID;
    }

    // ---------------------- BUILDER ----------------------
    public static class Builder {
        private String id;
        private Vehicle vehicle;
        private ParkingSpot spot;
        private Instant entryTime;
        private Instant exitTime;
        private double amount;
        private TicketStatus status = TicketStatus.ACTIVE;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder setSpot(ParkingSpot spot) {
            this.spot = spot;
            return this;
        }

        public Builder setEntryTime(Instant entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public Builder setExitTime(Instant exitTime) {
            this.exitTime = exitTime;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setStatus(TicketStatus status) {
            this.status = status;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}
