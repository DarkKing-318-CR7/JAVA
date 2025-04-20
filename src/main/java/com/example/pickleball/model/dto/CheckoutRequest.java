package com.example.pickleball.model.dto;

public class CheckoutRequest {
    private String fullname;
    private String address;
    private String phone;

    // Getters and setters
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}