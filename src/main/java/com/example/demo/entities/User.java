package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 80)
    private String names;
    @NotBlank
    @Size(max = 80)
    private String lastname;
    @NotBlank
    @Size(max = 80)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String campus;
    @NotBlank
    @Size(max = 8, min = 8)
    private String dni;
    private LocalDate createdAt;
    @Email
    @NotBlank
    @Size(max = 80)
    private String email;
//AUTOR Y OBRA
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Rol> roles;
}
