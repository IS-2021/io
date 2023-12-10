package com.example.demoio.controllers.restapi;

import com.example.demoio.models.orm.User;
import com.example.demoio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BaseRestApiController {

    @Autowired
    protected ApplicationContext ctx;

    protected User getCurrentUser() {
        String username = getCurrentUserName();

        UserRepository userRepository = ctx.getBean(UserRepository.class);
        return userRepository.findByUsername(username);
    }

    protected String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
