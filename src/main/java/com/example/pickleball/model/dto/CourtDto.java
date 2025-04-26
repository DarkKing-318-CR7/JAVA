package com.example.pickleball.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourtDto {
    private Long id;
    private String name;
    private String location;
    private boolean active;
    private boolean available;
    private String description;
    private String imageUrl;
    private BigDecimal pricePerHour;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
