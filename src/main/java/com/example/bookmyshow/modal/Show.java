package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Screen screen;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShowStatus showStatus;

    @OneToMany(mappedBy = "show",cascade = CascadeType.DETACH)
    private List<ShowSeat> showSeats;
}
