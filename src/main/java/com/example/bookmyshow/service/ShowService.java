package com.example.bookmyshow.service;

import com.example.bookmyshow.dtos.ShowSeatDto;
import com.example.bookmyshow.dtos.TicketDto;
import com.example.bookmyshow.exceptions.SeatAreNotAvailable;
import com.example.bookmyshow.exceptions.ShowNotFound;
import com.example.bookmyshow.exceptions.ShowSeatNotFound;
import com.example.bookmyshow.exceptions.UnAuthorizeException;

import java.util.List;

public interface ShowService {
    List<ShowSeatDto> getShowSeats(Long userId,Long showId) throws ShowNotFound;
    TicketDto issueTicket(Long userId,List<Long> showSeats) throws ShowSeatNotFound, SeatAreNotAvailable, UnAuthorizeException;
}
