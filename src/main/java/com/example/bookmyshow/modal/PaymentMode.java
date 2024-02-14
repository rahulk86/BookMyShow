package com.example.bookmyshow.modal;

import lombok.Getter;

@Getter
public enum PaymentMode {
    CREDIT_CARD("credit card"),
    DEBIT_CARD("debit card"),
    NET_BANKING("net banking"),
    UPI("upi"),
    WALLET("wallet"),
    CASH("cash");

    private final String displayName;

    PaymentMode(String displayName) {
        this.displayName = displayName;
    }

    public static PaymentMode getPaymentMode(String paymentMode) {
        for (PaymentMode mode : values()) {
            if (mode.displayName.equalsIgnoreCase(paymentMode.trim())) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Invalid payment mode: " + paymentMode);
    }

}
