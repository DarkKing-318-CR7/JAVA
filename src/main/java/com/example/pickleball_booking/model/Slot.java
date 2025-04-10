package com.example.pickleball_booking.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time; // ví dụ: "10:00 - 11:00"

    private boolean available = true;

    // Nếu cần thêm ngày hoặc sân, bạn có thể thêm:
    // private LocalDate date;
    // @ManyToOne
    // private Court court;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
