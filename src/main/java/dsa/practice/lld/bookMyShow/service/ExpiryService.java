package dsa.practice.lld.bookMyShow.service;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExpiryService {
    private final BookingService bookingService;
    private final ScheduledExecutorService scheduler;

    private ScheduledFuture<?> task;

    public ExpiryService(BookingService bookingService) {
        this.bookingService = Objects.requireNonNull(bookingService);
        this.scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setName("booking-expiry-service");
            t.setDaemon(true);
            return t;
        });
    }

    public void start(long period, TimeUnit unit) {
        if (task != null && !task.isCancelled()) {
            return;
        }
        task = scheduler.scheduleAtFixedRate(bookingService::expireHeldBookings, period, period, unit);
    }

    public void stop() {
        if (task != null) {
            task.cancel(false);
        }
        scheduler.shutdownNow();
    }
}
