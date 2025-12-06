package dsa.practice.lld.parkingLot.vehicle;

import dsa.practice.lld.parkingLot.enums.VehicleType;

public abstract class Vehicle {
    private final String vehicleNumber;
    private final VehicleType type;

    public Vehicle(String vehicleNumber, VehicleType type) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getType() {
        return type;
    }
}
