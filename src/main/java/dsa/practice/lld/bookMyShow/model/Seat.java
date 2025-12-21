package dsa.practice.lld.bookMyShow.model;

import dsa.practice.lld.bookMyShow.enums.SeatType;

import java.util.Objects;

public record Seat(String seatId, SeatType seatType) {
    public Seat(String seatId, SeatType seatType) {
        this.seatId = Objects.requireNonNull(seatId);
        this.seatType = Objects.requireNonNull(seatType);
    }
}
