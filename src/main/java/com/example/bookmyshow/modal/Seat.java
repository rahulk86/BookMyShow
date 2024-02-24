package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat extends BaseModel {
    private String number;
    private Integer seatRow;
    private Integer seatCol;
    private SeatType seatType;
    @ManyToOne
    private Screen screen;
}
