package com.example.pickleball.Controller;


import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.Service.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @GetMapping
    public List<CourtDto> getAllCourts() {
        return courtService.getAllCourts();
    }

    @GetMapping("/{id}")
    public CourtDto getCourt(@PathVariable Long id) {
        return courtService.getCourtById(id);
    }

    @PostMapping
    public CourtDto createCourt(@RequestBody CourtDto courtDto) {
        return courtService.createCourt(courtDto);
    }

    @PutMapping("/{id}")
    public CourtDto updateCourt(@PathVariable Long id, @RequestBody CourtDto courtDto) {
        return courtService.updateCourt(id, courtDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCourt(@PathVariable Long id) {
        courtService.deleteCourt(id);
    }
}
