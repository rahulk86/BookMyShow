package com.example.bookmyshow.modal;

import lombok.Getter;

@Getter
public enum SeatType {
    GOLD(150.00),
    SILVER(200.00),
    DIAMOND(250.00);
    private final double price;
    SeatType(double price) {
        this.price = price;
    }
}
