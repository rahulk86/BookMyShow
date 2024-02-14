package com.example.bookmyshow.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieDto {
    private Long id;
    private String name;
    private double rating;
    private String imageUrl;
    private String genre;
}
