package com.example.bookmyshow.service.Impl;

import com.example.bookmyshow.dtos.MovieDto;
import com.example.bookmyshow.dtos.ShowDto;
import com.example.bookmyshow.dtos.ShowTypeDto;
import com.example.bookmyshow.dtos.TheaterDto;
import com.example.bookmyshow.exceptions.MovieExceptions;
import com.example.bookmyshow.modal.*;
import com.example.bookmyshow.repository.MovieRepository;
import com.example.bookmyshow.repository.ScreenRepository;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Override
    public List<MovieDto> getMovies(String regionName) {
        List<Movie> movies = movieRepository.findByRegionName(regionName);
        return movies
                .stream()
                .map(movie -> MovieDto
                                .builder()
                                .id(movie.getId())
                                .genre(movie.getGenre())
                                .name(movie.getName())
                                .rating(movie.getRating())
                              .build()
                ).collect(Collectors.toList());
    }

    @Override
    public List<TheaterDto> getTheaters(long movieId) throws MovieExceptions {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty()){
            throw new MovieExceptions(movieId);
        }
        Movie movie = optionalMovie.get();
        List<Screen> screens = screenRepository.findScreensWithUpcomingShowsByMovie(movie);
        return screens
                .stream()
                .map(screen -> TheaterDto
                    .builder()
                    .id(screen.getTheater().getId())
                    .name(screen.getTheater().getName())
                    .shows(showDtos(screen.getShows()))
                    .build()
                ).collect(Collectors.toList());
    }

    private ShowTypeDto toShowTypeDto(Map<SeatType,Integer> seatTypes,SeatType seatType){
        Integer count = seatTypes.get(seatType);
        if(count==null){
            return ShowTypeDto
                    .builder()
                    .status(ShowSeatStatus.NOT_AVAILABLE)
                    .price(seatType.getPrice())
                    .seatType(seatType.name())
                    .build();
        }
        return ShowTypeDto
                .builder()
                .status(ShowSeatStatus.AVAILABLE)
                .price(seatType.getPrice())
                .seatType(seatType.name())
                .build();
    }
    private List<ShowTypeDto> getSeatAvailability(List<ShowSeat> showSeats){
        Map<SeatType,Integer> showSeatStatus = new HashMap<>();
        showSeats.forEach(showSeat -> {
            SeatType seatType = showSeat.getSeat().getSeatType();
            showSeatStatus.put(seatType,showSeatStatus.getOrDefault(seatType,0)+1);
        });
        List<ShowTypeDto> showTypeDtos = new ArrayList<>();
        showTypeDtos.add(toShowTypeDto(showSeatStatus,SeatType.GOLD));
        showTypeDtos.add(toShowTypeDto(showSeatStatus,SeatType.DIAMOND));
        showTypeDtos.add(toShowTypeDto(showSeatStatus,SeatType.SILVER));
        return showTypeDtos;
    }

    private List<ShowDto> showDtos(List<Show> shows){
        return shows
                .stream()
                .map(show -> ShowDto
                    .builder()
                    .id(show.getId())
                    .showTypes(getSeatAvailability(show.getShowSeats()))
                    .startTime(show.getStartTime())
                    .build()
                )
                .collect(Collectors.toList());
    }
}
