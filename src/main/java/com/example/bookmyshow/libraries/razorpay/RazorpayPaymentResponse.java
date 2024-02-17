package com.example.bookmyshow.libraries.razorpay;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RazorpayPaymentResponse {
    private String transactionId;
    private String paymentStatus;
    private String orderId;
    private double transactionAmount;
    private Date transactionDate;
}