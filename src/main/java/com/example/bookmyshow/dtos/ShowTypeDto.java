package com.example.bookmyshow.dtos;

import com.example.bookmyshow.modal.ShowSeatStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShowTypeDto {
    private Double price;
    private String seatType;
    private ShowSeatStatus status;
}
