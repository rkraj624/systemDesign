package dsa.practice.lld.parkingLot.vehicle;

import dsa.practice.lld.parkingLot.enums.VehicleType;

public class Bike extends Vehicle{
    public Bike(String vehicleNumber) {
        super(vehicleNumber, VehicleType.BIKE);
    }
}
