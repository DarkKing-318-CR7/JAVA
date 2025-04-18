package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    // Optional: Tránh trùng sân + thời gian
    boolean existsByCourtIdAndDateAndTime(Long courtId, LocalDate date, LocalTime time);
}
