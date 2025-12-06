package dsa.practice.lld.parkingLot.strategy;

import dsa.practice.lld.parkingLot.ParkingLot;
import dsa.practice.lld.parkingLot.ParkingSpot;
import dsa.practice.lld.parkingLot.enums.VehicleType;

public interface ParkingSpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, ParkingLot parkingLot);
}
