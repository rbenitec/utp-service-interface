package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUtpClient {
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String names;
    private String lastname;
    private String dni;
}
