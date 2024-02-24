package com.example.bookmyshow.service.Impl;

import com.auth.modal.user.User;
import com.auth.repository.user.UserRepository;
import com.example.bookmyshow.adapters.PaymentGatewayAdapter;
import com.example.bookmyshow.dtos.PaymentDetails;
import com.example.bookmyshow.dtos.ShowSeatDto;
import com.example.bookmyshow.dtos.TicketDto;
import com.example.bookmyshow.exceptions.*;
import com.example.bookmyshow.modal.*;
import com.example.bookmyshow.repository.PaymentRepository;
import com.example.bookmyshow.repository.ShowRepository;
import com.example.bookmyshow.repository.ShowSeatRepository;
import com.example.bookmyshow.repository.TicketRepository;
import com.example.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    private PaymentGatewayAdapter<Ticket> paymentGatewayAdapter;
    @Autowired
    private PaymentRepository<Ticket> paymentRepository;
    @Override
    public List<ShowSeatDto> getShowSeats(Long userId,Long showId) throws ShowNotFound {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElse(null);
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
                                        .price(showSeat.getSeat().getSeatType().getPrice())
                                        .type(showSeat.getSeat().getSeatType().name())
                                        .status(getShowSeatStatus(user,showSeat))
                                        .build()
                    )
                    .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 60000)
    public void updateTicketStatus() {
        List<ShowSeat> reservedTickets = showSeatRepository.findByStatus(ShowSeatStatus.RESERVED);

        for (ShowSeat ticket : reservedTickets) {
            ticket.setStatus(ShowSeatStatus.AVAILABLE);
        }
        showSeatRepository.saveAll(reservedTickets);
    }

    private String getShowSeatStatus(User user,ShowSeat seat){
        if((seat.getUser()!=null && seat.getUser().equals(user)
            && seat.getStatus().equals(ShowSeatStatus.RESERVED))
        || seat.getStatus().equals(ShowSeatStatus.AVAILABLE)){
           return ShowSeatStatus.AVAILABLE.name();
        }
        return ShowSeatStatus.NOT_AVAILABLE.name();
    }

    @Override
    public TicketDto issueTicket(Long userId,List<Long> showSeats) throws ShowSeatNotFound, SeatAreNotAvailable, UnAuthorizeException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UnAuthorizeException(userId);
        }
        User user = optionalUser.get();
        List<ShowSeat> seats = showSeats
                                    .stream()
                                    .map(seatId -> {
                                        Optional<ShowSeat> showSeat = showSeatRepository.findById(seatId);
                                        if (showSeat.isEmpty()) {
                                            throw new ShowSeatNotFound(seatId);
                                        }
                                        ShowSeat seat = showSeat.get();
                                        if ((user == seat.getUser()
                                            && showSeat.get().getStatus().equals(ShowSeatStatus.RESERVED))
                                            || showSeat.get().getStatus().equals(ShowSeatStatus.AVAILABLE)) {
                                            seat.setUser(user);
                                            seat.setStatus(ShowSeatStatus.RESERVED);
                                            return showSeatRepository.save(seat);
                                        }
                                        throw new SeatAreNotAvailable();
                                    })
                                    .collect(Collectors.toList());
        Ticket ticket = new Ticket();
        ticket.setTicketId(generateTicketId());
        ticket.setBaseAmount(98.00);
        ticket.setGstPercentage(18.0);
        ticket.setSeats(seats);
        ticket.setUser(user);
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
                                .price(showSeat.getSeat().getSeatType().getPrice())
                                .type(showSeat.getSeat().getSeatType().name())
                                .col(showSeat.getSeat().getSeatCol())
                                .build())
                            .collect(Collectors.toList())
                           )
                .build();
    }

    @Override
    public PaymentDetails makePayment(String ticketId) throws TicketNotFoundException {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if(optionalTicket.isEmpty()){
          throw new TicketNotFoundException(ticketId);
        }
        Payment<Ticket> payment = paymentGatewayAdapter.makePayment(optionalTicket.get());
        payment.setPaymentMode(PaymentMode.CASH);
        if(payment.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            payment.getTaxable()
                    .getSeats()
                    .forEach(showSeat -> {
                        if(!showSeat.getStatus().equals(ShowSeatStatus.RESERVED)){
                            throw new ShowSeatNotFound(showSeat.getId());
                        }
                        showSeat.setStatus(ShowSeatStatus.BOOKED);
                        showSeatRepository.save(showSeat);
                    });
        }
        Payment<Ticket> ticketPayment = paymentRepository.save(payment);
        return PaymentDetails
                .builder()
                .paymentMode(ticketPayment.getPaymentMode().name())
                .baseAmount(ticketPayment.getTaxable().getBaseAmount())
                .paymentStatus(ticketPayment.getPaymentStatus().name())
                .taxAmount(ticketPayment.getTaxAmount())
                .gstPercentage(ticketPayment.getTaxable().getGstPercentage())
                .referenceNumber(ticketPayment.getReferenceNumber())
                .totalAmount(ticketPayment.getTotalAmount())
                .build();
    }

    public static String generateTicketId() {
        String prefix = "TICKET";
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return prefix + uniqueId;
    }
}
