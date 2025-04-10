package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "USERS")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    public AppUser(String username, String password, String name, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}
