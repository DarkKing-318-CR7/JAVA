package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    List<Court> findByActiveTrue();
}
