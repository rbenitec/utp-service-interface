package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResponseUtpClient {
    private String username;
    private String password;
    private String email;
    private LocalDate createdAt;
    private String names;
    private String lastname;
    private String dni;
}
