package com.example.bookmyshow.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TicketDto {
    private String ticketId;
    private List<ShowSeatDto> showSeats;
    private double baseAmount;
    private double gstPercentage;
}
