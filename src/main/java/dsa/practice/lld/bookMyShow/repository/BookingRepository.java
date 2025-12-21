package dsa.practice.lld.bookMyShow.repository;

import dsa.practice.lld.bookMyShow.model.Booking;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookingRepository {
    private final ConcurrentMap<String, Booking> bookingsById = new ConcurrentHashMap<>();

    public void save(Booking booking) {
        Objects.requireNonNull(booking);
        bookingsById.put(booking.getBookingId(), booking);
    }

    public Optional<Booking> findById(String bookingId) {
        return Optional.ofNullable(bookingsById.get(bookingId));
    }

    public List<Booking> findAll() {
        return List.copyOf(bookingsById.values());
    }
}
