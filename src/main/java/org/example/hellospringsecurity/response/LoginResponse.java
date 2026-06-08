package org.example.hellospringsecurity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private Long id;

    private String username;

    private String fullName;

    private String role;
}