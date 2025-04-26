package com.example.pickleball.Service.Impl;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.entity.Booking;
import com.example.pickleball.model.entity.BookingStatus;
import com.example.pickleball.model.entity.Court;
import com.example.pickleball.model.entity.User;
import com.example.pickleball.Repositories.BookingRepository;
import com.example.pickleball.Repositories.CourtRepository;
import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CourtRepository courtRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Long getUserIdByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user"))
                .getId();
    }


    @Override
    public void createBooking(BookingDto dto) {
        Booking booking = new Booking();
        booking.setDate(dto.getDate());
        booking.setTime(dto.getTime());
        booking.setNotes(dto.getNotes());
        booking.setStatus(BookingStatus.PENDING);
        booking.setDuration(dto.getDuration());

        Court court = courtRepository.findById(dto.getCourtId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        booking.setCourt(court);
        booking.setUser(user);

        bookingRepository.save(booking);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDto getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow();
    }

    @Override
    public void updateBooking(Long id, BookingDto dto) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        booking.setDate(dto.getDate());
        booking.setTime(dto.getTime());
        booking.setNotes(dto.getNotes());
        booking.setStatus(BookingStatus.valueOf(dto.getStatus()));

        if (dto.getCourtId() != null) {
            booking.setCourt(courtRepository.findById(dto.getCourtId()).orElseThrow());
        }

        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingDto> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private BookingDto mapToDto(Booking booking) {
        BookingDto dto = modelMapper.map(booking, BookingDto.class);
        dto.setUserId(booking.getUser().getId());
        dto.setCourtId(booking.getCourt().getId());
        dto.setCourtName(booking.getCourt().getName());
        dto.setStatus(booking.getStatus().name());
        dto.setDuration(booking.getDuration());
        dto.setUserName(booking.getUser().getUsername());
        return dto;
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }


}
