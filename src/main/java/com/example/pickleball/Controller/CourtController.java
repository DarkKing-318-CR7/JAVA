package com.example.pickleball.Controller;


import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.Service.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @GetMapping
    public ResponseEntity<List<CourtDto>> getAllCourts() {
        return ResponseEntity.ok(courtService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourtDto> getCourtById(@PathVariable Long id) {
        return ResponseEntity.ok(courtService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourtDto> createCourt(@RequestBody CourtDto dto) {
        return ResponseEntity.ok(courtService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourtDto> updateCourt(@PathVariable Long id, @RequestBody CourtDto dto) {
        return ResponseEntity.ok(courtService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourt(@PathVariable Long id) {
        courtService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
