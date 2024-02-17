package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.ShowSeat;
import com.example.bookmyshow.modal.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findByStatus(ShowSeatStatus status);
}
