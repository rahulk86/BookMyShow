package com.example.bookmyshow.service;

import com.example.bookmyshow.dtos.PaymentDetails;
import com.example.bookmyshow.dtos.ShowSeatDto;
import com.example.bookmyshow.dtos.TicketDto;
import com.example.bookmyshow.exceptions.*;

import java.util.List;

public interface ShowService {
    List<ShowSeatDto> getShowSeats(Long userId,Long showId) throws ShowNotFound;
    TicketDto issueTicket(Long userId,List<Long> showSeats) throws ShowSeatNotFound, SeatAreNotAvailable, UnAuthorizeException;
    PaymentDetails makePayment(String ticketId) throws TicketNotFoundException;
}
