package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.Booking;
import com.example.pickleball_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")


public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking_list"; // Trả về file booking-list.html trong templates
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form"; // Trả về file booking-form.html trong templates
    }

    @PostMapping
    public String createBooking(@ModelAttribute Booking booking) {
        bookingService.createBooking(booking);
        return "redirect:/bookings"; // Chuyển hướng về danh sách bookings
    }
}
