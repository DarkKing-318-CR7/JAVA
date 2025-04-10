package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.Booking;
import com.example.pickleball_booking.model.Slot;
import com.example.pickleball_booking.repository.BookingRepository;
import com.example.pickleball_booking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

//    public Booking bookCourt(Booking booking) {
//        return bookingRepository.save(booking);
//    }
    @Autowired
    private SlotRepository slotRepository;
    // BookingService.java
    public void bookCourt(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        Booking booking = new Booking();
        booking.setSlot(slot);
        // Có thể gán thêm user hiện tại, ngày đặt...

        bookingRepository.save(booking);
    }


    public List<Booking> getAvailableSlots() {
        // Giả sử bạn có một phương thức để lấy danh sách các slot có sẵn
        // Bạn có thể cần phải điều chỉnh logic này tùy thuộc vào cách bạn quản lý các slot
        return bookingRepository.findAvailableSlots(); // Phương thức này cần được định nghĩa trong BookingRepository
    }

}