package com.sabancidx.productapi.service;

import com.sabancidx.productapi.entity.Role;
import com.sabancidx.productapi.entity.User;
import com.sabancidx.productapi.repository.RolesRepository;
import com.sabancidx.productapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    public User register(String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = rolesRepository.findByAuthority("USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setAuthorities(roles);
        return userRepository.save(user);
    }
}
