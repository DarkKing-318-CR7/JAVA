package com.example.pickleball.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private Long courtId;
    private String customerName;
    private String customerEmail;
    private LocalDateTime bookingDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isConfirmed;
}
