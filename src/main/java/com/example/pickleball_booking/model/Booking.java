package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bookingTime; // Thời gian đặt sân

    @ManyToOne
    private AppUser  user; // Người đặt sân
    private String courtLocation; // Vị trí sân
    @ManyToOne
    private Slot slot;

}