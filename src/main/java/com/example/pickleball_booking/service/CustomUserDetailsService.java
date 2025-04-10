package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.AppUser ;
import com.example.pickleball_booking.model.Role;
import com.example.pickleball_booking.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser  user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User  not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .build();
    }
}