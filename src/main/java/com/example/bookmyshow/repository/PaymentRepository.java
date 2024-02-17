package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Payment;
import com.example.bookmyshow.modal.Taxable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PaymentRepository<T extends Taxable> extends JpaRepository<Payment<T>, Long> {
    Optional<Payment<T>> findByTaxable(T taxable);
}
