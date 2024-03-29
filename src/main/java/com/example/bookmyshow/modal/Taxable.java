package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Taxable extends BaseModel {
    private Double baseAmount;
    private Double gstPercentage;
    public Double getTotalAmount(){
        return baseAmount + getTaxAmount()+getPrice();
    }

    public abstract Double getPrice() ;

    public Double getTaxAmount(){
        return baseAmount*(gstPercentage/100.0);
    }
}