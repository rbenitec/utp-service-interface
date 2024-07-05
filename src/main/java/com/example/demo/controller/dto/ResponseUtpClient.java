package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUtpClient {
    private String username;
    private String password;
    private String email;
    private LocalDate createdAt;
    private String names;
    private String lastname;
    private String dni;
    private String campus;
}
