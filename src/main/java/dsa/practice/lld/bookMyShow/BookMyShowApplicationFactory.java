package dsa.practice.lld.bookMyShow;

import dsa.practice.lld.bookMyShow.lock.LockManager;
import dsa.practice.lld.bookMyShow.payment.MockPaymentGateway;
import dsa.practice.lld.bookMyShow.payment.PaymentGateway;
import dsa.practice.lld.bookMyShow.repository.BookingRepository;
import dsa.practice.lld.bookMyShow.repository.PaymentRepository;
import dsa.practice.lld.bookMyShow.repository.ShowRepository;
import dsa.practice.lld.bookMyShow.repository.ShowSeatRepository;
import dsa.practice.lld.bookMyShow.service.BookingService;
import dsa.practice.lld.bookMyShow.service.ExpiryService;
import dsa.practice.lld.bookMyShow.service.PaymentService;
import dsa.practice.lld.bookMyShow.service.ShowService;

import java.time.Duration;
import java.util.Random;

public class BookMyShowApplicationFactory {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Duration holdDuration = Duration.ofMinutes(5);
        private long randomSeed = 7L;
        private long expiryPeriodSeconds = 1L;

        public Builder holdDuration(Duration holdDuration) {
            this.holdDuration = holdDuration;
            return this;
        }

        public Builder randomSeed(long randomSeed) {
            this.randomSeed = randomSeed;
            return this;
        }

        public Builder expiryPeriodSeconds(long expiryPeriodSeconds) {
            this.expiryPeriodSeconds = expiryPeriodSeconds;
            return this;
        }

        public BookMyShowApplication buildAndStartExpiry() {
            ShowRepository showRepository = new ShowRepository();
            ShowSeatRepository showSeatRepository = new ShowSeatRepository();
            BookingRepository bookingRepository = new BookingRepository();
            PaymentRepository paymentRepository = new PaymentRepository();

            LockManager lockManager = new LockManager();

            ShowService showService = new ShowService(showRepository, showSeatRepository);
            BookingService bookingService = new BookingService(bookingRepository, showSeatRepository, lockManager, holdDuration);

            PaymentGateway paymentGateway = new MockPaymentGateway(new Random(randomSeed));
            PaymentService paymentService = new PaymentService(paymentRepository, paymentGateway);

            ExpiryService expiryService = new ExpiryService(bookingService);
            expiryService.start(expiryPeriodSeconds, java.util.concurrent.TimeUnit.SECONDS);

            return new BookMyShowApplication(showService, bookingService, paymentService, expiryService);
        }
    }
}
