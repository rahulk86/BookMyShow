package com.example.bookmyshow.service.Impl;

import com.example.bookmyshow.dtos.ShowSeatDto;
import com.example.bookmyshow.dtos.TicketDto;
import com.example.bookmyshow.exceptions.SeatAreNotAvailable;
import com.example.bookmyshow.exceptions.ShowNotFound;
import com.example.bookmyshow.exceptions.ShowSeatNotFound;
import com.example.bookmyshow.exceptions.UnAuthorizeException;
import com.example.bookmyshow.modal.*;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.repository.ShowSeatRepository;
import com.example.bookmyshow.repository.TicketRepository;
import com.example.bookmyshow.repository.UserRepository;
import com.example.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public List<ShowSeatDto> getShowSeats(Long showId) throws ShowNotFound {
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
          throw new ShowNotFound(showId);
        }
        return optionalShow.get()
                    .getShowSeats()
                    .stream()
                    .map(showSeat -> ShowSeatDto
                                        .builder()
                                        .id(showSeat.getId())
                                        .col(showSeat.getSeat().getSeatCol())
                                        .row(showSeat.getSeat().getSeatRow())
                                        .status(showSeat.getStatus().name())
                                        .build()
                    )
                    .collect(Collectors.toList());
    }

    @Override
    public TicketDto issueTicket(Long userId,List<Long> showSeats) throws ShowSeatNotFound, SeatAreNotAvailable, UnAuthorizeException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UnAuthorizeException(userId);
        }

        List<ShowSeat> seats = showSeats
                                    .stream()
                                    .map(seatId -> {
                                        Optional<ShowSeat> showSeat = showSeatRepository.findById(seatId);
                                        if (showSeat.isEmpty()) {
                                            throw new ShowSeatNotFound(seatId);
                                        }
                                        if (!showSeat.get().getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                                            throw new SeatAreNotAvailable();
                                        }
                                        return showSeat.get();
                                    })
                                    .collect(Collectors.toList());
        Ticket ticket = new Ticket();
        ticket.setTicketId(generateTicketId());
        ticket.setBaseAmount(98.00);
        ticket.setGstPercentage(18.0);
        ticket.setSeats(seats);
        ticket.setUser(optionalUser.get());
        Ticket saveTicket = ticketRepository.save(ticket);
        return TicketDto
                .builder()
                .baseAmount(ticket.getBaseAmount())
                .ticketId(ticket.getTicketId())
                .gstPercentage(ticket.getGstPercentage())
                .showSeats(seats
                            .stream()
                            .map(showSeat -> ShowSeatDto
                                .builder()
                                .id(showSeat.getId())
                                .status(showSeat.getStatus().name())
                                .row(showSeat.getSeat().getSeatRow())
                                .col(showSeat.getSeat().getSeatCol())
                                .build()).collect(Collectors.toList()))
                .build();
    }

    public static String generateTicketId() {
        String prefix = "TICKET";
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return prefix + uniqueId;
    }
}
