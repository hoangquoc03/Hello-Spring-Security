package org.example.hellospringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/api/v1/auth/test")
    public String test() {
        return "Public API";
    }
}