package com.example.pickleball.model.dto;

import lombok.*;


@Data
public class CourtDto {
    private Long id;
    private String name;
    private String location;
    private boolean active;
    private String description;
}
