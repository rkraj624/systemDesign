package dsa.practice.lld.bookMyShow.factory;

import dsa.practice.lld.bookMyShow.enums.SeatType;

import java.util.EnumMap;
import java.util.Map;

public class DefaultSeatPricingStrategy implements SeatPricingStrategy {
    private final Map<SeatType, Integer> prices = new EnumMap<>(SeatType.class);

    public DefaultSeatPricingStrategy(int silver, int gold, int platinum) {
        prices.put(SeatType.SILVER, silver);
        prices.put(SeatType.GOLD, gold);
        prices.put(SeatType.PLATINUM, platinum);
    }

    @Override
    public int priceFor(SeatType seatType) {
        Integer p = prices.get(seatType);
        if (p == null) {
            throw new IllegalArgumentException("No price configured for seatType=" + seatType);
        }
        return p;
    }
}
