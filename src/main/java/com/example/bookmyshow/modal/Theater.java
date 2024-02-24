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
@Table(name = "theaters")
public class Theater extends BaseModel {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    @ManyToOne
    private Region region;
}
