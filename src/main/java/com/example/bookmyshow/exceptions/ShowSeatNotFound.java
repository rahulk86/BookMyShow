package com.example.bookmyshow.exceptions;

public class ShowSeatNotFound extends RuntimeException{
    public ShowSeatNotFound(Long showSeatId){
        super("showSeat not found with given id : "+showSeatId);
    }
}
