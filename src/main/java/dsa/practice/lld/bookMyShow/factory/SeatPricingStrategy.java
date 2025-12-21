package dsa.practice.lld.bookMyShow.factory;

import dsa.practice.lld.bookMyShow.enums.SeatType;

public interface SeatPricingStrategy {
    int priceFor(SeatType seatType);
}
