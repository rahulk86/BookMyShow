package com.example.bookmyshow.adapters;

import com.example.bookmyshow.modal.Payment;
import com.example.bookmyshow.modal.Taxable;

public interface PaymentGatewayAdapter<T extends Taxable> {
    Payment<T> makePayment(T taxable);
}
