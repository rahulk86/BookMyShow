package com.example.bookmyshow.exceptions;

public class MovieExceptions extends Exception{

    public MovieExceptions(Long movieId){
        super("movie not found with given id : "+movieId);
    }
}
