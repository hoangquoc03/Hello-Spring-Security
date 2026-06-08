package org.example.hellospringsecurity.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.hellospringsecurity.dto.LoginRequest;
import org.example.hellospringsecurity.dto.RegisterRequest;
import org.example.hellospringsecurity.model.User;
import org.example.hellospringsecurity.repository.UserRepository;
import org.example.hellospringsecurity.response.LoginResponse;
import org.example.hellospringsecurity.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return "Register successfully";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request,
            HttpSession session) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("username or password incorrect");
        }

        boolean matched = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matched) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("username or password incorrect");
        }

        session.setAttribute("LOGIN_USER", user);

        return ResponseEntity.ok(
                new LoginResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole()
                )
        );
    }

}