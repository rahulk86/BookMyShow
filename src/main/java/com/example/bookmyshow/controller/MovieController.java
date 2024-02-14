package com.example.bookmyshow.controller;

import com.example.bookmyshow.dtos.MovieDto;
import com.example.bookmyshow.dtos.TheaterDto;
import com.example.bookmyshow.payload.request.MovieRequest;
import com.example.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getMovies")
    public ResponseEntity<?> getMovies(@RequestBody MovieRequest movieRequest){
        List<MovieDto> movies = movieService.getMovies(movieRequest.getRegionName());
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/getTheaters/{movieId}")
    public ResponseEntity<?> getTheater(@PathVariable Long movieId){
        try {
            List<TheaterDto> theaters = movieService.getTheaters(movieId);
            return ResponseEntity.ok(theaters);
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }



}
