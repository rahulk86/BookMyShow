package com.example.bookmyshow.configuration;

import com.example.bookmyshow.modal.User;

import java.util.Optional;

public class AuditorAwareImpl {
//    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(new User());
        // Can use Spring Security to return currently logged in user
        // return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()
    }
}