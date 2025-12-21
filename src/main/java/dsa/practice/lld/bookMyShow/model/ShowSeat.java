package dsa.practice.lld.bookMyShow.model;

import dsa.practice.lld.bookMyShow.enums.SeatStatus;

import java.time.Instant;
import java.util.Objects;

public class ShowSeat {
    private final Seat seat;
    private final int price;

    private SeatStatus status;
    private String heldByBookingId;
    private Instant holdUntil;

    public ShowSeat(Seat seat, int price) {
        this.seat = Objects.requireNonNull(seat);
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat getSeat() {
        return seat;
    }

    public String getSeatId() {
        return seat.seatId();
    }

    public int getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public String getHeldByBookingId() {
        return heldByBookingId;
    }

    public Instant getHoldUntil() {
        return holdUntil;
    }

    public void hold(String bookingId, Instant holdUntil) {
        this.status = SeatStatus.HELD;
        this.heldByBookingId = bookingId;
        this.holdUntil = holdUntil;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
        this.heldByBookingId = null;
        this.holdUntil = null;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
        this.heldByBookingId = null;
        this.holdUntil = null;
    }
}
