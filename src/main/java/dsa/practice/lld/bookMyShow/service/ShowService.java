package dsa.practice.lld.bookMyShow.service;

import dsa.practice.lld.bookMyShow.model.Show;
import dsa.practice.lld.bookMyShow.model.ShowSeat;
import dsa.practice.lld.bookMyShow.repository.ShowRepository;
import dsa.practice.lld.bookMyShow.repository.ShowSeatRepository;

import java.util.Map;
import java.util.Objects;

public class ShowService {
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    public ShowService(ShowRepository showRepository, ShowSeatRepository showSeatRepository) {
        this.showRepository = Objects.requireNonNull(showRepository);
        this.showSeatRepository = Objects.requireNonNull(showSeatRepository);
    }

    public void addShow(Show show) {
        showRepository.save(show);
    }

    public void addShowSeat(String showId, ShowSeat showSeat) {
        showSeatRepository.addShowSeat(showId, showSeat);
    }

    public Map<String, Show> listShows() {
        return showRepository.findAll();
    }

    public Map<String, ShowSeat> getSeatMap(String showId) {
        return showSeatRepository.findAllByShowId(showId);
    }
}
