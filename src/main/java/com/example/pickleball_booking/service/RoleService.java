package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.Role;

import com.example.pickleball_booking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Lấy vai trò theo tên
    public Optional<Role> getRoleByName(String name) {
        return Optional.ofNullable(roleRepository.findByName(name));
    }

    // Tạo mới vai trò
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Lấy tất cả các vai trò
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
