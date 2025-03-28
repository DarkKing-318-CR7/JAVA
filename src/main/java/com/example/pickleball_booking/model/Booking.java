package com.example.pickleball_booking.model;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Liên kết với User qua user_id
    private AppUser user;

    private String bookingDate;
    private String bookingTime;
    private String courtName;

    // Constructors
    public Booking() {}

    public Booking(AppUser user, String bookingDate, String bookingTime, String courtName) {
        this.user = user;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.courtName = courtName;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }
}
