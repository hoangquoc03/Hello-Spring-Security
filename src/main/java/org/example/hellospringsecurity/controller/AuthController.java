package org.example.hellospringsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.hellospringsecurity.dto.RegisterRequest;
import org.example.hellospringsecurity.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        authService.register(request);

        return "Register successfully";
    }
    @GetMapping("/api/v1/auth/test")
    public String test() {
        return "Public API";
    }

}