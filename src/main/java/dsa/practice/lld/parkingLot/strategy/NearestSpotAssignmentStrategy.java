package dsa.practice.lld.parkingLot.strategy;

import dsa.practice.lld.parkingLot.ParkingFloor;
import dsa.practice.lld.parkingLot.ParkingLot;
import dsa.practice.lld.parkingLot.ParkingSpot;
import dsa.practice.lld.parkingLot.enums.VehicleType;

public class NearestSpotAssignmentStrategy implements ParkingSpotAssignmentStrategy {

    @Override
    public ParkingSpot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) {
        for (ParkingFloor floor : parkingLot.getFloors()) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.isFree() && spot.getType().name().equals(vehicleType.name())) {
                    return spot;
                }
            }
        }
        return null; // or throw exception
    }
}
