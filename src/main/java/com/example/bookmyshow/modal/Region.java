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
@Table(name = "regions")
public class Region extends BaseModel {
   private String name;
   private Double latitude;
   private Double longitude;
   @OneToMany(mappedBy = "region",cascade = CascadeType.DETACH)
   private List<Theater> theaters;
}
