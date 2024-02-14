package com.example.bookmyshow.service;

import com.example.bookmyshow.dtos.MovieDto;
import com.example.bookmyshow.dtos.TheaterDto;
import com.example.bookmyshow.exceptions.MovieExceptions;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies(String regionName);
    List<TheaterDto> getTheaters(long movieId) throws MovieExceptions;
}
