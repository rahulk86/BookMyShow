<h1 align="center">Design a Movie Ticket Booking System</h1>
<h3 align="center">Let's design Movie Ticket Booking System</h3>

## Description
This Spring Boot application is a Movie Ticket Booking System that allows customers to browse movies, book seats, and manage their bookings. The system supports features like listing cities, managing cinemas, halls, movies, shows, seats, bookings, customers, and discount coupons.

## Table of Contents
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Testing](#testing)
- [Contributors](#contributors)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Requirements
- Java 8 or later
- Maven
- MySQL database

## Getting Started

**We'll cover the following:**
An online movie ticket booking system facilitates the purchasing of movie tickets to its customers. E-ticketing systems allow customers to browse through movies currently playing and book seats, anywhere and anytime.


Our ticket booking service should meet the following requirements:

1. It should be able to list the cities where affiliate cinemas are located.
2. Each cinema can have multiple halls and each hall can run one movie show at a time.
3. Each Movie will have multiple shows.
4. Customers should be able to search movies by their title, language, genre, release date, and city name.
5. Once the customer selects a movie, the service should display the cinemas running that movie and its available shows.
6. The customer should be able to select a show at a particular cinema and book their tickets.
7. The service should show the customer the seating arrangement of the cinema hall. The customer should be able to select multiple seats according to their preference.
8. The customer should be able to distinguish between available seats and booked ones.
9. The system should send notifications whenever there is a new movie, as well as when a booking is made or canceled.
10. Customers of our system should be able to pay with credit cards or cash.
11. The system should ensure that no two customers can reserve the same seat.
12. Customers should be able to add a discount coupon to their payment.