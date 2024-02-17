package com.example.bookmyshow.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShowSeatDto {
    private Long id;
    private int row;
    private int col;
    private double price;
    private String type;
    private String status;
}
