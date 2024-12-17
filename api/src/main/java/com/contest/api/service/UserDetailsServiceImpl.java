package com.contest.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.contest.api.entity.Roles;
import com.contest.api.entity.User;
import com.contest.api.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepository;

    public UserDetailsServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String[] roles = user.getRoles().stream()
                .map(Roles::name)
                .toArray(String[]::new);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles) // Default role
                .build();
    }
}

