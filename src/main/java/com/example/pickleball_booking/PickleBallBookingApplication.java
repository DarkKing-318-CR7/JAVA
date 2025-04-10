package com.example.pickleball_booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PickleBallBookingApplication {

    public static void main(String[] args) {
        // Loại bỏ sự phụ thuộc vào CRaCMXBean
        SpringApplication.run(PickleBallBookingApplication.class, args);
    }
}
