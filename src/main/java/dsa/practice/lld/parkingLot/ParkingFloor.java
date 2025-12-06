package dsa.practice.lld.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private final String floorId;
    private final List<ParkingSpot> spots = new ArrayList<>();

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public String getFloorId() {
        return floorId;
    }
}
