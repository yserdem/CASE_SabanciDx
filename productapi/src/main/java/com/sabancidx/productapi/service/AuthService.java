package com.sabancidx.productapi.service;

import com.sabancidx.productapi.dto.LoginResponse;
import com.sabancidx.productapi.entity.Role;
import com.sabancidx.productapi.entity.User;
import com.sabancidx.productapi.repository.RolesRepository;
import com.sabancidx.productapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    @Autowired
    public AuthService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User register(String email, String password) {

        Optional<User> foundUser = userRepository.findUserByEmail(email);
        if(foundUser.isPresent()) return null;

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

    public LoginResponse login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            String token = tokenService.generateJwtToken(authentication);
            return new LoginResponse(token);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new LoginResponse("");
        }
    }
}
