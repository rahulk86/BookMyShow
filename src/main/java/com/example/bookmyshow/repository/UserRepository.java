package com.example.bookmyshow.repository;

import com.example.bookmyshow.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
