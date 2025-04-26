package com.example.pickleball.Controller;

import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.Service.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/courts")
@RequiredArgsConstructor
public class AdminCourtController {

    private final CourtService courtService;

    // 1. Hiển thị trang quản lý sân
    @GetMapping
    public String viewCourts(Model model) {
        List<CourtDto> courts = courtService.getAll();
        model.addAttribute("courts", courts);
        return "admin/admin-courts"; // tên file HTML admin-courts.html
    }

    // 2. Tạo sân mới (nhận từ form modal popup)
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createCourt(@RequestBody CourtDto courtDto) {
        try {
            CourtDto createdCourt = courtService.create(courtDto);
            return ResponseEntity.ok(createdCourt);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating court");
        }
    }
    // 3. API lấy chi tiết sân theo ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getCourtById(@PathVariable Long id) {
        try {
            CourtDto court = courtService.getById(id);
            return ResponseEntity.ok(court);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Court not found");
        }
    }

    // 4. API xóa sân theo ID
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCourt(@PathVariable Long id) {
        try {
            courtService.delete(id);
            return ResponseEntity.ok("Xóa sân thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Xóa sân thất bại");
        }
    }
}

