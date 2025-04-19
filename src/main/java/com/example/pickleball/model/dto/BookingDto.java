package com.example.pickleball.model.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {

    private Long id;

    private LocalDate date;

    private LocalTime time;

    private String notes;

    private Long userId;

    private Long courtId;
    private Integer duration;

    private String courtName; // Optional: dùng để hiển thị lại

    private String status; // Optional: hiển thị trạng thái (PENDING, CONFIRMED, etc.)
}
