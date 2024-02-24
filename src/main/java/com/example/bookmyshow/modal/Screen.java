package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "screens")
public class Screen extends BaseModel {
    private String name;

    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private List<Seat> seats;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    private List<Show> shows;

    @ElementCollection
    @Enumerated(value = EnumType.ORDINAL)
    private List<Features> features;
}
