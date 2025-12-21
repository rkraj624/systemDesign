package dsa.practice.lld.bookMyShow.service;

import dsa.practice.lld.bookMyShow.enums.BookingStatus;
import dsa.practice.lld.bookMyShow.enums.SeatStatus;
import dsa.practice.lld.bookMyShow.exception.BookingNotFoundException;
import dsa.practice.lld.bookMyShow.exception.InvalidBookingStateException;
import dsa.practice.lld.bookMyShow.exception.SeatNotAvailableException;
import dsa.practice.lld.bookMyShow.lock.LockManager;
import dsa.practice.lld.bookMyShow.model.Booking;
import dsa.practice.lld.bookMyShow.model.ShowSeat;
import dsa.practice.lld.bookMyShow.repository.BookingRepository;
import dsa.practice.lld.bookMyShow.repository.ShowSeatRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowSeatRepository showSeatRepository;
    private final LockManager lockManager;
    private final Duration holdDuration;

    public BookingService(BookingRepository bookingRepository,
                          ShowSeatRepository showSeatRepository,
                          LockManager lockManager,
                          Duration holdDuration) {
        this.bookingRepository = Objects.requireNonNull(bookingRepository);
        this.showSeatRepository = Objects.requireNonNull(showSeatRepository);
        this.lockManager = Objects.requireNonNull(lockManager);
        this.holdDuration = Objects.requireNonNull(holdDuration);
    }

    public Booking holdSeats(String userId, String showId, List<String> seatIds) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(showId);
        Objects.requireNonNull(seatIds);
        if (seatIds.isEmpty()) {
            throw new IllegalArgumentException("seatIds cannot be empty");
        }

        return lockManager.withShowLock(showId, () -> {
            for (String seatId : seatIds) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(showId, seatId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid seatId: " + seatId));
                if (showSeat.getStatus() != SeatStatus.AVAILABLE) {
                    throw new SeatNotAvailableException("Seat not available: " + seatId);
                }
            }

            String bookingId = UUID.randomUUID().toString();
            Instant now = Instant.now();
            Instant expiresAt = now.plus(holdDuration);

            for (String seatId : seatIds) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(showId, seatId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid seatId: " + seatId));
                showSeat.hold(bookingId, expiresAt);
            }

            Booking booking = new Booking(bookingId, userId, showId, seatIds, now, expiresAt);
            bookingRepository.save(booking);
            return booking;
        });
    }

    public Booking getBooking(String bookingId) {
        Objects.requireNonNull(bookingId);
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));
    }

    public Booking markPaymentFailed(String bookingId) {
        Objects.requireNonNull(bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        return lockManager.withShowLock(booking.getShowId(), () -> {
            if (booking.getStatus() == BookingStatus.PAYMENT_FAILED) {
                return booking;
            }
            if (booking.getStatus() != BookingStatus.HELD) {
                throw new InvalidBookingStateException("Cannot mark payment failed in state: " + booking.getStatus());
            }

            String id = booking.getBookingId();
            for (String seatId : booking.getSeatIds()) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(booking.getShowId(), seatId)
                        .orElseThrow(() -> new IllegalStateException("Seat missing: " + seatId));

                if (showSeat.getStatus() == SeatStatus.HELD && id.equals(showSeat.getHeldByBookingId())) {
                    showSeat.release();
                }
            }

            booking.setStatus(BookingStatus.PAYMENT_FAILED);
            bookingRepository.save(booking);
            return booking;
        });
    }

    public Booking confirmBooking(String bookingId) {
        Objects.requireNonNull(bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        return lockManager.withShowLock(booking.getShowId(), () -> {
            if (booking.getStatus() == BookingStatus.CONFIRMED) {
                return booking;
            }
            if (booking.getStatus() != BookingStatus.HELD) {
                throw new InvalidBookingStateException("Cannot confirm booking in state: " + booking.getStatus());
            }

            Instant now = Instant.now();
            if (now.isAfter(booking.getExpiresAt())) {
                expireBookingInternal(booking, now);
                throw new InvalidBookingStateException("Booking expired: " + bookingId);
            }

            for (String seatId : booking.getSeatIds()) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(booking.getShowId(), seatId)
                        .orElseThrow(() -> new IllegalStateException("Seat missing: " + seatId));

                if (showSeat.getStatus() != SeatStatus.HELD || !bookingId.equals(showSeat.getHeldByBookingId())) {
                    throw new SeatNotAvailableException("Seat no longer held for this booking: " + seatId);
                }
                if (showSeat.getHoldUntil() != null && now.isAfter(showSeat.getHoldUntil())) {
                    expireBookingInternal(booking, now);
                    throw new InvalidBookingStateException("Booking expired during confirmation: " + bookingId);
                }
            }

            for (String seatId : booking.getSeatIds()) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(booking.getShowId(), seatId)
                        .orElseThrow(() -> new IllegalStateException("Seat missing: " + seatId));
                showSeat.book();
            }
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
            return booking;
        });
    }

    public Booking cancelBooking(String bookingId) {
        Objects.requireNonNull(bookingId);
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));

        return lockManager.withShowLock(booking.getShowId(), () -> {
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                return booking;
            }
            if (booking.getStatus() != BookingStatus.HELD && booking.getStatus() != BookingStatus.CONFIRMED) {
                throw new InvalidBookingStateException("Cannot cancel booking in state: " + booking.getStatus());
            }

            for (String seatId : booking.getSeatIds()) {
                ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(booking.getShowId(), seatId)
                        .orElseThrow(() -> new IllegalStateException("Seat missing: " + seatId));

                if (bookingId.equals(showSeat.getHeldByBookingId()) && showSeat.getStatus() == SeatStatus.HELD) {
                    showSeat.release();
                }
            }

            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
            return booking;
        });
    }

    public void expireHeldBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        Instant now = Instant.now();
        int expired = 0;

        for (Booking booking : allBookings) {
            if (booking.getStatus() != BookingStatus.HELD) {
                continue;
            }
            if (!now.isAfter(booking.getExpiresAt())) {
                continue;
            }

            boolean didExpire = lockManager.withShowLock(booking.getShowId(), () -> expireBookingInternal(booking, now));
            if (didExpire) {
                expired++;
            }
        }
        System.out.println("Expired :"+ expired);
    }

    private boolean expireBookingInternal(Booking booking, Instant now) {
        if (booking.getStatus() != BookingStatus.HELD) {
            return false;
        }
        if (!now.isAfter(booking.getExpiresAt())) {
            return false;
        }

        String bookingId = booking.getBookingId();
        for (String seatId : booking.getSeatIds()) {
            ShowSeat showSeat = showSeatRepository.findByShowIdAndSeatId(booking.getShowId(), seatId)
                    .orElseThrow(() -> new IllegalStateException("Seat missing: " + seatId));

            if (showSeat.getStatus() == SeatStatus.HELD && bookingId.equals(showSeat.getHeldByBookingId())) {
                showSeat.release();
            }
        }

        booking.setStatus(BookingStatus.EXPIRED);
        bookingRepository.save(booking);
        return true;
    }
}
