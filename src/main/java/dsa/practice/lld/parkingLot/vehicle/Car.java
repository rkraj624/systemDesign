package dsa.practice.lld.parkingLot.vehicle;

import dsa.practice.lld.parkingLot.enums.VehicleType;

public class Car extends Vehicle{
    public Car(String vehicleNumber) {
        super(vehicleNumber, VehicleType.CAR);
    }
}
