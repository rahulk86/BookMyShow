package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Movie;
import com.example.bookmyshow.modal.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    @Query("SELECT DISTINCT s FROM Screen s " +
            "LEFT JOIN FETCH s.shows sh " +
            "WHERE sh.showStatus = ShowStatus.Upcoming AND sh.movie = ?1")
    List<Screen> findScreensWithUpcomingShowsByMovie(Movie movie);
}
