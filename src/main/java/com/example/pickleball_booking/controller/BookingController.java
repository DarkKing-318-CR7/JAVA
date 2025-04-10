package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.Booking;
import com.example.pickleball_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public String bookingPage(Model model) {
        model.addAttribute("availableSlots", bookingService.getAvailableSlots());
        return "booking";
    }

    @PostMapping("/booking")
    public String bookCourt(@RequestParam("slotId") Long slotId) {
        bookingService.bookCourt(slotId);
        return "redirect:/account";
    }

}