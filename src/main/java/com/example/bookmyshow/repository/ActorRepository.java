package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.Actors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actors,Long> {
}
