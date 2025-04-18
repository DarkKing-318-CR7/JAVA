package com.example.pickleball.Service.Impl;

import com.example.pickleball.exception.ResourceNotFoundException;
import com.example.pickleball.model.dto.CourtDto;
import com.example.pickleball.model.entity.Court;
import com.example.pickleball.Repositories.CourtRepository;
import com.example.pickleball.Service.CourtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final ModelMapper modelMapper;

    public CourtServiceImpl(CourtRepository courtRepository, ModelMapper modelMapper) {
        this.courtRepository = courtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourtDto> getAllCourts() {
        List<Court> courts = courtRepository.findAll();
        return courts.stream()
                .map(court -> modelMapper.map(court, CourtDto.class))  // Chuyển Court thành CourtDto
                .collect(Collectors.toList());
    }

    @Override
    public CourtDto getCourtById(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Court not found with id: " + id));
        return modelMapper.map(court, CourtDto.class);  // Chuyển Court thành CourtDto
    }

    @Override
    public CourtDto createCourt(CourtDto courtDto) {
        Court court = modelMapper.map(courtDto, Court.class);  // Chuyển CourtDto thành Court
        Court savedCourt = courtRepository.save(court);
        return modelMapper.map(savedCourt, CourtDto.class);  // Chuyển lại thành CourtDto
    }

    @Override
    public CourtDto updateCourt(Long id, CourtDto courtDto) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Court not found with id: " + id));

        court.setName(courtDto.getName());
        court.setLocation(courtDto.getLocation());
        court.setType(courtDto.getType());
        court.setAvailable(courtDto.isAvailable());
        court.setPricePerHour(courtDto.getPricePerHour());

        Court updatedCourt = courtRepository.save(court);
        return modelMapper.map(updatedCourt, CourtDto.class);  // Chuyển lại thành CourtDto
    }

    @Override
    public void deleteCourt(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Court not found with id: " + id));
        courtRepository.delete(court);
    }
}
