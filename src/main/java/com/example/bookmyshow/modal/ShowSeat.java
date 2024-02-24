package com.example.bookmyshow.modal;

import com.auth.modal.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "showSeats")
public class ShowSeat extends com.auth.modal.BaseModel {
     @ManyToOne
     private User user;
     @ManyToOne
     private Seat seat;
     @ManyToOne
     private Show show;
     private ShowSeatStatus status;
}
