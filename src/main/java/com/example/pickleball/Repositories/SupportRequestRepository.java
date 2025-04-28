package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long> {
}
