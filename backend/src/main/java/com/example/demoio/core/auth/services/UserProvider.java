package com.example.demoio.core.auth.services;

import com.example.demoio.models.User;
import com.example.demoio.core.auth.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProvider {
    private final UserRepository userRepository;

    public UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getCurrentUserId() {
        String username = getCurrentUserName();

        return this.userRepository.findByUsername(username).getUserId();
    }

    public User getCurrentUser() {
        String username = getCurrentUserName();

        return this.userRepository.findByUsername(username);
    }

    protected String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
