package com.contest.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.contest.api.entity.LoginRequest;
import com.contest.api.entity.Roles;
import com.contest.api.entity.User;
import com.contest.api.repository.UserRepo;
import com.contest.api.service.SessionManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SessionManager sessionManager;

    

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
            User user = userRepo.findByEmailOrUserName(loginRequest.getUsername()).get();
            String sessionCookie = this.sessionManager.createSession(user); 
            return ResponseEntity.ok()
            .header("Set-Cookie", "JSESSION_ID=" + sessionCookie + "; Path=/; HttpOnly; SameSite=None; Secure")
            .body("Login successful");
        
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
    // Check Authentication Status
    @GetMapping("/status")
    public ResponseEntity<?> authStatus(Principal p) {
        System.out.println(p.getClass().getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        if (principal instanceof User user) {
            return ResponseEntity.ok(user.getUserId());
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
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
