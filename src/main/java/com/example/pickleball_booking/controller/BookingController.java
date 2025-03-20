package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.Booking;
import com.example.pickleball_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String getBookingForm() {
        return "booking_form";
    }

    @GetMapping("/list")
    public String getBookingList(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("booking", bookings);
        return "booking_list";
    }
}