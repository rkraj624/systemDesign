package dsa.practice.lld.bookMyShow;

import dsa.practice.lld.bookMyShow.factory.DefaultSeatPricingStrategy;
import dsa.practice.lld.bookMyShow.factory.ShowSeatFactory;
import dsa.practice.lld.bookMyShow.enums.SeatType;
import dsa.practice.lld.bookMyShow.model.Booking;
import dsa.practice.lld.bookMyShow.model.Show;
import dsa.practice.lld.bookMyShow.model.ShowSeat;
import dsa.practice.lld.bookMyShow.service.ShowService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        BookMyShowApplication app = BookMyShowApplicationFactory.builder()
                .holdDuration(Duration.ofSeconds(5))
                .randomSeed(7L)
                .expiryPeriodSeconds(1)
                .buildAndStartExpiry();

        ShowService showService = app.showService();

        ShowSeatFactory showSeatFactory = new ShowSeatFactory(new DefaultSeatPricingStrategy(150, 200, 300));

        String showId = "show-1";
        showService.addShow(new Show(showId, "Interstellar", Instant.now().plusSeconds(3600), Duration.ofMinutes(180)));
        ShowSeat a1 = showSeatFactory.create("A1", SeatType.GOLD);
        ShowSeat a2 = showSeatFactory.create("A2", SeatType.GOLD);
        ShowSeat a3 = showSeatFactory.create("A3", SeatType.SILVER);
        showService.addShowSeat(showId, a1);
        showService.addShowSeat(showId, a2);
        showService.addShowSeat(showId, a3);

        CountDownLatch start = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            await(start);
            try {
                Booking b = app.checkout("user-1", showId, List.of("A1", "A2"));
                System.out.println("user-1 checkout result: " + b.getBookingId() + " status=" + b.getStatus());
            } catch (Exception e) {
                System.out.println("user-1 failed: " + e.getMessage());
            }
        });

        executor.submit(() -> {
            await(start);
            try {
                Booking b = app.checkout("user-2", showId, List.of("A1"));
                System.out.println("user-2 checkout result: " + b.getBookingId() + " status=" + b.getStatus());
            } catch (Exception e) {
                System.out.println("user-2 failed: " + e.getMessage());
            }
        });

        start.countDown();

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        Booking held = app.bookingService().holdSeats("user-3", showId, List.of("A3"));
        System.out.println("user-3 held booking (will expire): " + held.getBookingId() + " status=" + held.getStatus());

        Thread.sleep(6500);

        System.out.println("Final seat map:");

        Map<String, ShowSeat> seatMap = showService.getSeatMap(showId);
        seatMap.values().forEach(seat ->
                System.out.println(seat.getSeatId() + " -> " + seat.getStatus())
        );

        app.expiryService().stop();
    }

    private static void await(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
