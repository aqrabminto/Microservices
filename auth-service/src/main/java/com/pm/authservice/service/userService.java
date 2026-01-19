package com.pm.authservice.service;

import com.pm.authservice.model.User;
import com.pm.authservice.repository.userRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userService {
    private final userRepository userRepository;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
