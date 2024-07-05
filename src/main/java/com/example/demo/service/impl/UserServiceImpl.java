package com.example.demo.service.impl;

import com.example.demo.entities.ERole;
import com.example.demo.entities.Rol;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private RoleService rolService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.authenticate(username, password);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getName(String username) {
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
        //return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}