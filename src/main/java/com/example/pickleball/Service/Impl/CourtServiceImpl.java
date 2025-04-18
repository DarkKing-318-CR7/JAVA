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
@RequiredArgsConstructor
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CourtDto> getAll() {
        return courtRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CourtDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourtDto getById(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Court not found"));
        return modelMapper.map(court, CourtDto.class);
    }

    @Override
    public CourtDto create(CourtDto dto) {
        Court court = modelMapper.map(dto, Court.class);
        return modelMapper.map(courtRepository.save(court), CourtDto.class);
    }

    @Override
    public CourtDto update(Long id, CourtDto dto) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Court not found"));

        court.setName(dto.getName());
        court.setLocation(dto.getLocation());
        court.setDescription(dto.getDescription());
        court.setActive(dto.isActive());

        return modelMapper.map(courtRepository.save(court), CourtDto.class);
    }

    @Override
    public void delete(Long id) {
        courtRepository.deleteById(id);
    }
}
