package com.example.pickleball.Service;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;

import java.util.List;

public interface BookingService {
    List<BookingDto> getAllBookings();
    List<BookingDto> getBookingsByUser(Long userId);
    BookingDto createBooking(BookingDto bookingDto);
    void deleteBooking(Long id);
}

