package com.contest.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.contest.api.entity.LoginRequest;
import com.contest.api.entity.Roles;
import com.contest.api.entity.User;
import com.contest.api.repository.UserRepo;

import jakarta.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // User Registration Endpoint
     @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        try {
            if (userRepo.findByEmailOrUserName(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email or username already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setCreatedAt(Instant.now());
            user.setRoles(Set.of(Roles.USER)); // Set default role
            userRepo.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), 
                    loginRequest.getPassword()
                )
            );
            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
    // Check Authentication Status
    @GetMapping("/status")
    public String authStatus(Principal principal) {
        if (principal != null) {
            return "Logged in as: " + principal.getName();
        } else {
            return "Not authenticated";
        }
    }

    // Logout Endpoint (Spring Security handles session invalidation)
    @PostMapping("/logout")
    public String logout() {
        return "User logged out successfully";
    }

    // Secured Endpoint Example
    @GetMapping("/home")
    public String home(Principal principal) {
        return "Welcome to the home page, " + principal.getName();
    }
}
