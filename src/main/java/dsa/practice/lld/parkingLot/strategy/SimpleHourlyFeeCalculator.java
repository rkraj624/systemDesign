package dsa.practice.lld.parkingLot.strategy;

import dsa.practice.lld.parkingLot.enums.VehicleType;

import java.time.Duration;
import java.time.Instant;

public class SimpleHourlyFeeCalculator implements ParkingFeeCalculatorStrategy {

    @Override
    public double calculateFee(Instant entryTime, Instant exitTime, VehicleType type) {
        long hours = Math.max(1, Duration.between(entryTime, exitTime).toHours());
        double ratePerHour = switch (type) {
            case BIKE -> 20;
            case CAR -> 40;
            case TRUCK -> 60;
            default -> 100;
        };
        return hours * ratePerHour;
    }
}
