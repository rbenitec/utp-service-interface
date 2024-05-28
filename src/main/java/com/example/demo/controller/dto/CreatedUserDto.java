package com.example.demo.controller.dto;

import com.example.demo.entities.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedUserDto {
    @NotBlank
    private String names;
    @NotBlank
    private String lastname;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String dni;
    @Email
    @NotBlank
    private String email;
    private Set<String> roles;
}
