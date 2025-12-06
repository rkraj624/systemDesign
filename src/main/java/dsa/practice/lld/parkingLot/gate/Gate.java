package dsa.practice.lld.parkingLot.gate;

import dsa.practice.lld.parkingLot.ParkingLot;

public abstract class Gate {
    protected String id;
    protected ParkingLot parkingLot;

    public Gate(String id, ParkingLot parkingLot) {
        this.id = id;
        this.parkingLot = parkingLot;
    }

    public String getId() {
        return id;
    }
}
