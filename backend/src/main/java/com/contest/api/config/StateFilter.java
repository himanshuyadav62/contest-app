package com.contest.api.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.contest.api.entity.User;
import com.contest.api.service.SessionManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class StateFilter extends OncePerRequestFilter {

    private SessionManager sessionManager;

    public StateFilter(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String sessionCookie = null;
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                sessionCookie = Arrays.stream(cookies)
                        .filter(cookie -> "JSESSION_ID".equals(cookie.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        }

        if (sessionCookie != null && sessionManager.validateSession(sessionCookie)) {
            User user = this.sessionManager.getUser(sessionCookie);
            var authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .toList();

            // Create Authentication object with user and authorities
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

            // Set Authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
