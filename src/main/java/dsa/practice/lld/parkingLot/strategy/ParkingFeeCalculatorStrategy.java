package dsa.practice.lld.parkingLot.strategy;

import dsa.practice.lld.parkingLot.enums.VehicleType;

import java.time.Instant;

public interface ParkingFeeCalculatorStrategy {
    double calculateFee(Instant entryTime, Instant exitTime, VehicleType type);
}
