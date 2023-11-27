package com.example.demoio.controllers.restapi;

import com.example.demoio.User;
import com.example.demoio.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/gamedata")
public class Game extends BaseRestApiController {

    @GetMapping("/{gameid}")
    public String tt(@PathVariable int gameid) {

        HttpServletResponse response;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRepository userRepository = getCtx().getBean(UserRepository.class);
        User user =  userRepository.findByUsername(auth.getName());

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", "Hello, JSON!");

        // Use Jackson ObjectMapper to convert data to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        //String jsonResponse = objectMapper.writeValueAsString(responseData);

        // Set content type and write JSON response
        //response.setContentType("application/json");
        //response.getWriter().write(jsonResponse);
        //wyciagamy uzytkownika, czyli jego coisy

        return "";
    }

    @PostMapping("/{id}")
    public String handleSave(@PathVariable int id) {

        //przyjmujemy id usera, updatujemy coinsy i zapisujemy progress uzytkownika

        return "tt";
    }
}
