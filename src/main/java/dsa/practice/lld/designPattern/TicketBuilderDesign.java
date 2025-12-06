package dsa.practice.lld.designPattern;

import dsa.practice.lld.parkingLot.ParkingSpot;
import dsa.practice.lld.parkingLot.enums.TicketStatus;
import dsa.practice.lld.parkingLot.vehicle.Vehicle;

import java.time.Instant;

public class TicketBuilderDesign {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final Instant entryTime;
    private Instant exitTime;
    private double amount;
    private TicketStatus status = TicketStatus.ACTIVE;

    public static void main(String[] args) {
        TicketBuilderDesign ticket = new Builder()
                .setId("xyz")
                .setAmount(500)
                .setEntryTime(Instant.now())
                .setStatus(TicketStatus.ACTIVE)
                .build();
        System.out.println(ticket);
    }

    private TicketBuilderDesign(Builder builder) {
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

        public TicketBuilderDesign build() {
            return new TicketBuilderDesign(this);
        }
    }

    @Override
    public String toString() {
        return "TicketBuilderDesign{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", spot=" + spot +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
