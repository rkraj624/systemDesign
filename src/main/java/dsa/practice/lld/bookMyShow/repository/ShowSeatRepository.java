package dsa.practice.lld.bookMyShow.repository;

import dsa.practice.lld.bookMyShow.model.ShowSeat;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ShowSeatRepository {
    private final ConcurrentMap<String, ConcurrentMap<String, ShowSeat>> showSeatByShowId = new ConcurrentHashMap<>();

    public void addShowSeat(String showId, ShowSeat showSeat) {
        Objects.requireNonNull(showId);
        Objects.requireNonNull(showSeat);
        showSeatByShowId.computeIfAbsent(showId, k -> new ConcurrentHashMap<>())
                .put(showSeat.getSeatId(), showSeat);
    }

    public Optional<ShowSeat> findByShowIdAndSeatId(String showId, String seatId) {
        Objects.requireNonNull(showId);
        Objects.requireNonNull(seatId);
        Map<String, ShowSeat> seats = showSeatByShowId.get(showId);
        if (seats == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(seats.get(seatId));
    }

    public Map<String, ShowSeat> findAllByShowId(String showId) {
        Objects.requireNonNull(showId);
        Map<String, ShowSeat> seats = showSeatByShowId.get(showId);
        if (seats == null) {
            return Map.of();
        }
        return Map.copyOf(seats);
    }
}
