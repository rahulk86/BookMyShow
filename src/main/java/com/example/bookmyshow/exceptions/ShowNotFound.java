package com.example.bookmyshow.exceptions;

public class ShowNotFound extends Exception{
    public ShowNotFound(Long showId){
        super("show not found with given id : "+showId);
    }
}
