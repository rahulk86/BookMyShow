package com.example.bookmyshow.modal;

import com.auth.modal.BaseModel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment <T extends Taxable> extends BaseModel {

    @Setter(value = AccessLevel.NONE)
    @ManyToOne(targetEntity = Taxable.class)
    private T taxable;
    public Payment(T taxable){
        this.taxable = taxable;
    }
    private double taxAmount;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String referenceNumber;
}
