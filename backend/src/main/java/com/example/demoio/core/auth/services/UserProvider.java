package com.example.demoio.core.auth.services;

import com.example.demoio.models.User;
import com.example.demoio.modules.datastorage.repositories.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserProvider {
    protected final ApplicationContext ctx;

    public UserProvider(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public User getCurrentUser() {
        String username = getCurrentUserName();

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        return userRepository.findByUsername(username);
    }

    protected String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
