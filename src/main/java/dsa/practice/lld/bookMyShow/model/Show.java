package dsa.practice.lld.bookMyShow.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record Show(String showId, String movieName, Instant startTime, Duration duration) {
    public Show(String showId, String movieName, Instant startTime, Duration duration) {
        this.showId = Objects.requireNonNull(showId);
        this.movieName = Objects.requireNonNull(movieName);
        this.startTime = Objects.requireNonNull(startTime);
        this.duration = Objects.requireNonNull(duration);
    }
}
