package com.example.bookmyshow.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment <T extends Taxable> extends AuditorEntity<User> {

    @ManyToOne(targetEntity = Taxable.class)
    private T taxable;

    private double taxAmount;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String referenceNumber;
}
