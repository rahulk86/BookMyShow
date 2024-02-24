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
@Table(name = "actors")
public class Actors extends BaseModel {
    private String name;
    @ManyToOne
    private Movie movie;
}
