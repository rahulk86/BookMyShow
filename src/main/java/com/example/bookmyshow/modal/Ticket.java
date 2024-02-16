package com.example.bookmyshow.modal;

import jakarta.persistence.*;
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
    @OneToMany
    private List<ShowSeat> seats;
}
