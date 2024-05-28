package com.example.demo.service;

import com.example.demo.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> getName(String username);
    User saveUser(User user);
    void deleteUser(Integer id);
}
