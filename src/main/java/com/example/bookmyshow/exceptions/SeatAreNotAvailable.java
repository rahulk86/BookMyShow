package com.example.bookmyshow.exceptions;

public class SeatAreNotAvailable extends RuntimeException{
    public SeatAreNotAvailable(){
        super("something are wrong refresh the page");
    }
}
