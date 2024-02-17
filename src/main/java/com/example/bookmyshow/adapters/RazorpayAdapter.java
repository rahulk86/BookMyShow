package com.example.bookmyshow.adapters;

import com.example.bookmyshow.libraries.razorpay.RazorpayApi;
import com.example.bookmyshow.libraries.razorpay.RazorpayPaymentResponse;
import com.example.bookmyshow.modal.Payment;
import com.example.bookmyshow.modal.PaymentStatus;
import com.example.bookmyshow.modal.Taxable;
import org.springframework.stereotype.Component;

@Component
public class RazorpayAdapter<T extends Taxable>  implements PaymentGatewayAdapter<T> {
    private final RazorpayApi razorpayApi;
    public RazorpayAdapter(){
        this.razorpayApi = new RazorpayApi();
    }
    @Override
    public Payment<T> makePayment(T taxable) {
        RazorpayPaymentResponse paytmPaymentResponse = razorpayApi.processPayment(taxable.getId(), taxable.getTotalAmount());
        Payment<T> payment = new Payment<>(taxable);
        payment.setPaymentStatus(PaymentStatus.valueOf(paytmPaymentResponse.getPaymentStatus()));
        payment.setReferenceNumber(paytmPaymentResponse.getTransactionId());
        payment.setTotalAmount(paytmPaymentResponse.getTransactionAmount());
        payment.setTaxAmount(taxable.getTaxAmount());
        return payment;
    }
}
