package com.example.pickleball.Service.Impl;

import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.exception.BadRequestException;
import com.example.pickleball.exception.ResourceNotFoundException;
import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;
import com.example.pickleball.model.entity.BookingStatus;
import com.example.pickleball.model.entity.Court;
import com.example.pickleball.Repositories.BookingRepository;
import com.example.pickleball.Repositories.CourtRepository;
import com.example.pickleball.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDto> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        boolean exists = bookingRepository.existsByCourtIdAndDateAndTime(
                bookingDto.getCourtId(), bookingDto.getDate(), bookingDto.getTime());

        if (exists) {
            throw new BadRequestException("Court is already booked at that time.");
        }

        Booking booking = new Booking();
        booking.setDate(bookingDto.getDate());
        booking.setTime(bookingDto.getTime());
        booking.setCourt(courtRepository.findById(bookingDto.getCourtId()).orElseThrow(
                () -> new ResourceNotFoundException("Court not found")));
        booking.setUser(userRepository.findById(bookingDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found")));
        booking.setStatus(BookingStatus.PENDING);

        return convertToDto(bookingRepository.save(booking));
    }


    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    private BookingDto convertToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setDate(booking.getDate());
        dto.setTime(booking.getTime());
        dto.setCourtId(booking.getCourt().getId());
        dto.setUserId(booking.getUser().getId());
        dto.setStatus(booking.getStatus());
        return dto;
    }
}
