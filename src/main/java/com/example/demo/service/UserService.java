package com.example.demo.service;

import com.example.demo.entities.ERole;
import com.example.demo.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> findByUsername(String username, String password);
    Optional<User> getName(String username);
    User saveUser(User user);
    void deleteUser(Integer id);
}
