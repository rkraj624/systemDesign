package dsa.practice.lld.bookMyShow.repository;

import dsa.practice.lld.bookMyShow.model.Show;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ShowRepository {
    private final ConcurrentMap<String, Show> showsById = new ConcurrentHashMap<>();

    public void save(Show show) {
        Objects.requireNonNull(show);
        showsById.put(show.showId(), show);
    }

    public Optional<Show> findById(String showId) {
        return Optional.ofNullable(showsById.get(showId));
    }

    public Map<String, Show> findAll() {
        return Map.copyOf(showsById);
    }
}
