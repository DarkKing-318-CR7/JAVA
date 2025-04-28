package com.example.pickleball.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupportDto {

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullname;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Chủ đề không được để trống")
    private String subject;

    @NotBlank(message = "Nội dung yêu cầu không được để trống")
    private String message;
}
