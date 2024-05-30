package com.example.demo.controller;

import com.example.demo.controller.dto.CreatedUserDto;
import com.example.demo.controller.dto.RequestUtpClient;
import com.example.demo.controller.dto.ResponseUtpClient;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Rol;
import com.example.demo.entities.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interface")
@RequiredArgsConstructor
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseUtpClient userAuthentication (@RequestBody RequestUtpClient requestUtpClient) {
        Optional<User> user = userService.findByUsername(requestUtpClient.getUsername());
        if(user.isPresent()){
            return builResponseUtpClient(user.get());
        }else{
            return null;
        }
    }
    @PostMapping("/created-user")
    public ResponseEntity<?> createdUser(@Valid @RequestBody CreatedUserDto userDto){
        Set<Rol> roles = userDto.getRoles().stream()
                .map(role -> Rol.builder()
                        .rol(ERole.valueOf(String.valueOf(role)))
                        .build())
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .names(userDto.getNames())
                .lastname(userDto.getLastname())
                .dni(userDto.getDni())
                .roles(roles)
                .createdAt(LocalDate.now())
                .build();

        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userService.deleteUser(Integer.parseInt(id));
        return "Se ha borrado el user con id: ".concat(id);
    }

    @GetMapping("/get-user")
    public ResponseEntity<User> getUser (@RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseUtpClient builResponseUtpClient(User user) {
        return ResponseUtpClient.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .names(user.getNames())
                .lastname(user.getLastname())
                .build();
    }
}
