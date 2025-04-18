package com.example.pickleball.model.dto;

import com.example.pickleball.model.entity.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long courtId;
    private Long userId;
    private BookingStatus status;
}
