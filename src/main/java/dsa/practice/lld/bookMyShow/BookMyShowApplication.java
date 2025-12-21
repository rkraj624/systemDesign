package dsa.practice.lld.bookMyShow;

import dsa.practice.lld.bookMyShow.enums.PaymentStatus;
import dsa.practice.lld.bookMyShow.model.Booking;
import dsa.practice.lld.bookMyShow.model.PaymentTransaction;
import dsa.practice.lld.bookMyShow.model.ShowSeat;
import dsa.practice.lld.bookMyShow.service.BookingService;
import dsa.practice.lld.bookMyShow.service.ExpiryService;
import dsa.practice.lld.bookMyShow.service.PaymentService;
import dsa.practice.lld.bookMyShow.service.ShowService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record BookMyShowApplication(ShowService showService, BookingService bookingService,
                                    PaymentService paymentService, ExpiryService expiryService) {
    public BookMyShowApplication(ShowService showService,
                                 BookingService bookingService,
                                 PaymentService paymentService,
                                 ExpiryService expiryService) {
        this.showService = Objects.requireNonNull(showService);
        this.bookingService = Objects.requireNonNull(bookingService);
        this.paymentService = Objects.requireNonNull(paymentService);
        this.expiryService = Objects.requireNonNull(expiryService);
    }

    public Booking checkout(String userId, String showId, List<String> seatIds) {
        Booking booking = bookingService.holdSeats(userId, showId, seatIds);

        int amount = 0;
        Map<String, ShowSeat> seatMap = showService.getSeatMap(showId);
        for (String seatId : seatIds) {
            ShowSeat seat = seatMap.get(seatId);
            if (seat == null) {
                throw new IllegalArgumentException("Invalid seatId: " + seatId);
            }
            amount += seat.getPrice();
        }

        PaymentTransaction tx = paymentService.initiatePayment(booking.getBookingId(), amount);
        tx = paymentService.processPayment(tx.getPaymentId());

        if (tx.getStatus() == PaymentStatus.SUCCESS) {
            return bookingService.confirmBooking(booking.getBookingId());
        }

        bookingService.markPaymentFailed(booking.getBookingId());
        return bookingService.getBooking(booking.getBookingId());
    }
}
