package dsa.practice.lld.bookMyShow.model;

import dsa.practice.lld.bookMyShow.enums.BookingStatus;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Booking {
    private final String bookingId;
    private final String userId;
    private final String showId;
    private final List<String> seatIds;
    private final Instant createdAt;
    private final Instant expiresAt;

    private BookingStatus status;

    public Booking(String bookingId, String userId, String showId, List<String> seatIds, Instant createdAt, Instant expiresAt) {
        this.bookingId = Objects.requireNonNull(bookingId);
        this.userId = Objects.requireNonNull(userId);
        this.showId = Objects.requireNonNull(showId);
        this.seatIds = Collections.unmodifiableList(List.copyOf(seatIds));
        this.createdAt = Objects.requireNonNull(createdAt);
        this.expiresAt = Objects.requireNonNull(expiresAt);
        this.status = BookingStatus.HELD;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getShowId() {
        return showId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = Objects.requireNonNull(status);
    }
}
