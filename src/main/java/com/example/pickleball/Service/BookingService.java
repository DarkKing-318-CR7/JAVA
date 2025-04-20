package com.example.pickleball.Service;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;

import java.util.List;

public interface BookingService {

    void createBooking(BookingDto bookingDto);

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(Long id);

    void updateBooking(Long id, BookingDto bookingDto);

    void deleteBooking(Long id);

    List<BookingDto> getBookingsByUserId(Long userId);
    List<Booking> getBookingsByUser(Long userId);

}
