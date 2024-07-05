package com.example.demo.service;

import com.example.demo.entities.ERole;
import com.example.demo.entities.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    Optional<User> authenticateUser(String username, String password);
    Optional<User> findByUsername(String username);
    Optional<User> getName(String username);
    User saveUser(User user);
    void deleteUser(Integer id);
}
