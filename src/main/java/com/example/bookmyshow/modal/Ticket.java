package com.example.bookmyshow.modal;

import com.auth.modal.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket extends Taxable{
    @ManyToOne
    private User user;
    private String ticketId;
    @ManyToMany
    private List<ShowSeat> seats;
    @Override
    public Double getPrice() {
        return seats.stream()
                    .map(showSeat -> showSeat.getSeat().getSeatType().getPrice())
                    .reduce(Double::sum)
                    .get();
    }

}
