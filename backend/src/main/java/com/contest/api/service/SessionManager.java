package com.contest.api.service;

import org.springframework.stereotype.Service;
import com.contest.api.entity.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManager {

    private final Map<String, User> sessions = new ConcurrentHashMap<>();

    public String createSession(User user) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, user);
        return sessionId;
    }

    public boolean validateSession(String session) {
        return sessions.containsKey(session);
    }

    public void deleteSession(String session) {
        sessions.remove(session);
    }

    public User getUser(String session) {
        return sessions.get(session);
    }
}
