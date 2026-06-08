package org.example.hellospringsecurity.service;

import lombok.RequiredArgsConstructor;
import org.example.hellospringsecurity.dto.RegisterRequest;
import org.example.hellospringsecurity.model.User;
import org.example.hellospringsecurity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setFullName(dto.getFullName());

        // QUAN TRỌNG
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setRole("USER");

        user.setEnabled(true);

        userRepository.save(user);
    }
}