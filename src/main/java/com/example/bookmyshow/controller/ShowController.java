package com.example.bookmyshow.controller;

import com.example.bookmyshow.dtos.ShowDto;
import com.example.bookmyshow.dtos.ShowSeatDto;
import com.example.bookmyshow.dtos.TicketDto;
import com.example.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movie/Show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @GetMapping("/getShowSeat/{userId}")
    public ResponseEntity<?> getShowSeat(@PathVariable Long userId,@RequestBody ShowDto showDto){
        try {
            List<ShowSeatDto> showSeats = showService.getShowSeats(userId,showDto.getId());
            return ResponseEntity.ok(showSeats);
        }
        catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getClass().getSimpleName()+" :: "+exception.getMessage());
        }
    }

    @PostMapping("/issueTicket/{userId}")
    public ResponseEntity<?> issueTicket(@PathVariable Long userId,@RequestBody List<ShowSeatDto> showSeatDtos){
        try {
            TicketDto ticketDto = showService.issueTicket(userId, showSeatDtos
                                                                    .stream()
                                                                    .map(ShowSeatDto::getId)
                                                                    .collect(Collectors.toList()));
            return ResponseEntity.ok(ticketDto);
        }
        catch (Exception exception){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getClass().getSimpleName()+" :: "+exception.getMessage());
        }
    }
}
