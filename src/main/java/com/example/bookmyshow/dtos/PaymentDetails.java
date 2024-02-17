package com.example.bookmyshow.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentDetails {
    private Double baseAmount;
    private Double gstPercentage;
    private double taxAmount;
    private double totalAmount;
    private String paymentMode;
    private String paymentStatus;
    private String referenceNumber;
}
