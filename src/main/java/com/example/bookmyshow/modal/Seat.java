package com.example.bookmyshow.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat extends AuditorEntity<User>{
    private String number;
    private Integer seatRow;
    private Integer seatCol;
    private SeatType seatType;
    @ManyToOne
    private Screen screen;
}
