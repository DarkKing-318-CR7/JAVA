package com.example.pickleball.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtDto {
    private Long id;
    private String name;
    private String location;
    private String type;
    private boolean available;
    private BigDecimal pricePerHour;
}
