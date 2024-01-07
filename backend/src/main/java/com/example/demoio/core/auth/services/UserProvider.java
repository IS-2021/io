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

    /**
     * Modify user's coins. Pass a negative value to remove coins.
     * @param coins The amount of coins to add/remove.
     */
    public void setUserCoins(int coins) {
        User user = this.getCurrentUser();
        user.setUserCoins(user.getUserCoins() + coins);

        if (user.getUserCoins() < 0) {
            user.setUserCoins(0);
        }

        this.userRepository.save(user);
    }
}
