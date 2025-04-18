package com.example.pickleball.Service;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking createBooking(BookingDto bookingDto);
    Booking updateBooking(Long id, BookingDto bookingDto);
    void cancelBooking(Long id);
}
