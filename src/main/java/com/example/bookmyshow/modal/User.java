package com.example.bookmyshow.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditorEntity<User> implements Serializable {
    private String name;
    private String email;
    private String password;
}