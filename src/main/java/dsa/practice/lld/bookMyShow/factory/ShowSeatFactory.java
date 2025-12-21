package dsa.practice.lld.bookMyShow.factory;

import dsa.practice.lld.bookMyShow.enums.SeatType;
import dsa.practice.lld.bookMyShow.model.Seat;
import dsa.practice.lld.bookMyShow.model.ShowSeat;

import java.util.Objects;

public class ShowSeatFactory {
    private final SeatPricingStrategy pricingStrategy;

    public ShowSeatFactory(SeatPricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy);
    }

    public ShowSeat create(String seatId, SeatType seatType) {
        Objects.requireNonNull(seatId);
        Objects.requireNonNull(seatType);
        int price = pricingStrategy.priceFor(seatType);
        return new ShowSeat(new Seat(seatId, seatType), price);
    }
}
