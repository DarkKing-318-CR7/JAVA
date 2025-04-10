package com.example.pickleball_booking.repository;

import com.example.pickleball_booking.model.AppUser ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser , Long> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}