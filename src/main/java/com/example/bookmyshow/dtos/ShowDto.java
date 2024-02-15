package com.example.bookmyshow.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ShowDto {
    private Long id;
    private LocalDateTime startTime;
    private List<ShowTypeDto> showTypes;
}
