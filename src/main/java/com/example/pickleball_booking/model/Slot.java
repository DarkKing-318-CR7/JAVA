package com.example.pickleball_booking.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime startTime; // Thời gian bắt đầu

    @Column(nullable = false)
    private LocalTime endTime; // Thời gian kết thúc

    private boolean available = true;

    // Nếu cần thêm ngày hoặc sân:
    // private LocalDate date;
    // @ManyToOne
    // private Court court;

    // Getters and Setters (Nếu dùng Lombok thì có thể bỏ qua)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
