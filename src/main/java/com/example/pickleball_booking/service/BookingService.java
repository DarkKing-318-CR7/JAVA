package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.Booking;
import com.example.pickleball_booking.model.AppUser;
import com.example.pickleball_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(AppUser user) {
        return bookingRepository.findByUser(user);
    }
}
