package com.example.pickleball.Service.Impl;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;
import com.example.pickleball.model.entity.Court;
import com.example.pickleball.Repositories.BookingRepository;
import com.example.pickleball.Repositories.CourtRepository;
import com.example.pickleball.Service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CourtRepository courtRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CourtRepository courtRepository) {
        this.bookingRepository = bookingRepository;
        this.courtRepository = courtRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public Booking createBooking(BookingDto bookingDto) {
        Court court = courtRepository.findById(bookingDto.getCourtId())
                .orElseThrow(() -> new RuntimeException("Court not found"));

        Booking booking = new Booking();
        booking.setCourt(court);
        booking.setCustomerName(bookingDto.getCustomerName());
        booking.setCustomerEmail(bookingDto.getCustomerEmail());
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setStartTime(bookingDto.getStartTime());
        booking.setEndTime(bookingDto.getEndTime());
        booking.setConfirmed(false);  // Default is not confirmed

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, BookingDto bookingDto) {
        Booking booking = getBookingById(id);
        Court court = courtRepository.findById(bookingDto.getCourtId())
                .orElseThrow(() -> new RuntimeException("Court not found"));

        booking.setCourt(court);
        booking.setCustomerName(bookingDto.getCustomerName());
        booking.setCustomerEmail(bookingDto.getCustomerEmail());
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setStartTime(bookingDto.getStartTime());
        booking.setEndTime(bookingDto.getEndTime());

        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }
}
