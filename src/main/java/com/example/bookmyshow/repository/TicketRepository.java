package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
