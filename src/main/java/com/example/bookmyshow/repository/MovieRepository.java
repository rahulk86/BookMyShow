package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m " +
            "FROM Movie m " +
            "INNER JOIN Show s ON s.movie = m " +
            "INNER JOIN s.screen.theater t ON t.region.name = ?1 " +
            "GROUP BY m")
    List<Movie> findByRegionName(String regionName);
}
