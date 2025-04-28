package com.example.pickleball.Controller;

import com.example.pickleball.model.dto.BookingDto;
import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.Service.BookingService;
import com.example.pickleball.Service.CourtService;
import com.example.pickleball.Service.UserService;
import com.example.pickleball.model.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CourtService courtService;

    @Autowired
    private UserService userService;

    // ✅ Hiển thị form đặt sân
    @GetMapping
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new BookingDto());

        List<CourtDto> courts = courtService.getAll(); // FIXED: đúng kiểu dữ liệu
        List<String> timeSlots = List.of("08:00", "09:00", "10:00", "11:00", "13:00", "14:00");

        model.addAttribute("availableCourts", courts);
        model.addAttribute("availableSlots", timeSlots);

        return "bookings/create";
    }

    // ✅ Xử lý form đặt sân
    @PostMapping
    public String createBooking(@ModelAttribute BookingDto bookingDto,
                                Principal principal,
                                Model model) {
        Long userId = userService.getUserIdByUsername(principal.getName());
        bookingDto.setUserId(userId);

        bookingService.createBooking(bookingDto);

        // Nạp lại dữ liệu cần thiết cho form
        List<CourtDto> courts = courtService.getAll();
        List<String> timeSlots = List.of("08:00", "09:00", "10:00", "11:00", "13:00", "14:00");

        model.addAttribute("booking", new BookingDto()); // reset form
        model.addAttribute("availableCourts", courts);
        model.addAttribute("availableSlots", timeSlots);
        model.addAttribute("success", "Đặt sân thành công!");

        return "bookings/create";
    }


    // ✅ Hiển thị lịch sử đặt sân của người dùng
    @GetMapping("/history")
    public String userBookingHistory(Model model, Principal principal) {
        Long userId = userService.getUserIdByUsername(principal.getName());
        List<BookingDto> bookings = bookingService.getBookingsByUserId(userId); // ✅ dùng BookingDto
        model.addAttribute("bookings", bookings);
        return "bookings/booking-history";
    }
    // ✅ Hủy đặt sân
    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam("bookingId") Long bookingId,
                                Principal principal,
                                RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByUsername(principal.getName());
        bookingService.cancelBooking(bookingId, userId);

        redirectAttributes.addFlashAttribute("success", "Đã hủy đặt sân thành công!");
        return "redirect:/bookings/history";
    }

}
