package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie extends BaseModel {
    private String name;
    private String language;
    private Double rating;
    private Double duration;
    private String genre;
    @OneToMany(mappedBy = "movie" ,cascade = CascadeType.DETACH)
    private List<Actors> actors;
}
