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

    @Column(nullable = false)
    private LocalDateTime bookingTime; // Thời gian đặt sân, bắt buộc

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading cho User
    @JoinColumn(name = "user_id") // Cải thiện việc mapping với cơ sở dữ liệu
    private AppUser user; // Người đặt sân

    @Column(nullable = false)
    private String courtLocation; // Vị trí sân, bắt buộc

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading cho Slot
    @JoinColumn(name = "slot_id") // Cải thiện việc mapping với cơ sở dữ liệu
    private Slot slot; // Slot của sân

    // Bạn có thể thêm một phương thức tiện ích để lấy thông tin booking chi tiết nếu cần
}
