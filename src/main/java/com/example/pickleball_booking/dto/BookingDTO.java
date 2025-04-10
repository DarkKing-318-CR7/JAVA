package com.example.pickleball_booking.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    private String courtLocation;
    private String bookingTime;
}