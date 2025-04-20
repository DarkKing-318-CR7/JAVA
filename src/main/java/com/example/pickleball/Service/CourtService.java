package com.example.pickleball.Service;

import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.model.entity.Court;

import java.util.List;

public interface CourtService {
    List<CourtDto> getAll();
    CourtDto getById(Long id);
    CourtDto create(CourtDto dto);
    CourtDto update(Long id, CourtDto dto);
    void delete(Long id);
}




