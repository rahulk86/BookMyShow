package com.example.bookmyshow.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket extends AuditorEntity<User>{
    @ManyToOne
    private User user;
    @OneToMany
    private List<ShowSeat> seats;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String referenceNumber;
}
