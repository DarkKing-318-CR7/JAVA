package com.example.pickleball.Service;

import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.model.entity.Court;

import java.util.List;

public interface CourtService {
    List<CourtDto> getAllCourts();  // Trả về List<CourtDto>
    CourtDto getCourtById(Long id);
    CourtDto createCourt(CourtDto courtDto);
    CourtDto updateCourt(Long id, CourtDto courtDto);
    void deleteCourt(Long id);
}



