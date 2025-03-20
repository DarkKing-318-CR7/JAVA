package com.example.pickleball_booking.repository;

import com.example.pickleball_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}