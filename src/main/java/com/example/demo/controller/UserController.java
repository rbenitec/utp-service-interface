package com.example.demo.controller;

import com.example.demo.controller.dto.CreatedUserDto;
import com.example.demo.controller.dto.RequestUtpClient;
import com.example.demo.controller.dto.ResponseErrorDto;
import com.example.demo.controller.dto.ResponseUtpClient;
import com.example.demo.entities.ERole;
import com.example.demo.entities.Rol;
import com.example.demo.entities.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interface")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseUtpClient userAuthentication (@RequestBody RequestUtpClient requestUtpClient) {
        log.info("POST --> Se llamo al endPoint authenticate: {}", requestUtpClient.toString());
        Optional<User> user = userService.authenticateUser(requestUtpClient.getUsername(), requestUtpClient.getPassword());
        //log.info("RESPONSE: {}", user.get());
        if(user.isPresent()){
            log.info("RESPONSE: {}", user.get());
            return builResponseUtpClient(user.get());
        }else{
            log.info("EL usuario no existe!");
            return null;
        }
    }
    @PostMapping("/created-user")
    public ResponseEntity<?> createdUser(@Valid @RequestBody CreatedUserDto userDto){
        Optional<User> userExist = userService.findByUsername(userDto.getUsername());
        if(userExist.isEmpty()){
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
        }else {
            ResponseErrorDto responseErrorDto =  new ResponseErrorDto(202, "EL usuario ingresado ya existe");
            return ResponseEntity.accepted().body(responseErrorDto);
        }
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userService.deleteUser(Integer.parseInt(id));
        return "Se ha borrado el user con id: ".concat(id);
    }

    @GetMapping("/get-user")
    public ResponseEntity<User> getUser (@RequestParam String username) {
        return null;
    }

    private ResponseUtpClient builResponseUtpClient(User user) {
        return ResponseUtpClient.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .names(user.getNames())
                .lastname(user.getLastname())
                .dni(user.getDni())
                .campus(user.getCampus())
                .build();
    }
}
