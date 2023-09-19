package com.sabancidx.productapi.controller;

import com.sabancidx.productapi.dto.LoginRequest;
import com.sabancidx.productapi.dto.LoginResponse;
import com.sabancidx.productapi.dto.RegistrationUser;
import com.sabancidx.productapi.entity.User;
import com.sabancidx.productapi.service.AuthService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public User register(@RequestBody @NotNull RegistrationUser registrationUser) {
        return authService.register(registrationUser.getEmail(), registrationUser.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @NotNull LoginRequest loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
