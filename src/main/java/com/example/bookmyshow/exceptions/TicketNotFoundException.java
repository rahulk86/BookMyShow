package com.example.bookmyshow.exceptions;

public class TicketNotFoundException extends Exception{
    public TicketNotFoundException(String ticketId){
        super("ticket not found with given id : "+ticketId);
    }
}
