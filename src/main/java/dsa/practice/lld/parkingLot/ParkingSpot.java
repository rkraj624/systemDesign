package dsa.practice.lld.parkingLot;

import dsa.practice.lld.parkingLot.enums.SpotType;
import dsa.practice.lld.parkingLot.vehicle.Vehicle;

public class ParkingSpot {
    private final String id;
    private final SpotType type;
    private boolean free;
    private Vehicle currentVehicle;

    public ParkingSpot(String id, SpotType type) {
        this.id = id;
        this.type = type;
        this.free = true;
    }

    public boolean isFree() {
        return free;
    }

    public SpotType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void park(Vehicle vehicle) {
        this.currentVehicle = vehicle;
        this.free = false;
    }

    public void unpark() {
        this.currentVehicle = null;
        this.free = true;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}

