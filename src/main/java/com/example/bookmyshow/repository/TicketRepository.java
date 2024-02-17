package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Optional<Ticket> findByTicketId(String ticketId);

}
