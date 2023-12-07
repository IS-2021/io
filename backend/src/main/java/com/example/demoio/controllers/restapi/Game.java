//package com.example.demoio.controllers.restapi;
//
//import com.example.demoio.User;
//import com.example.demoio.UserRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController("/gamedata")
//public class Game extends BaseRestApiController {
//
////    @GetMapping()
////    public String returnUserCoins() {
////        System.out.println("próba get");
////
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        UserRepository userRepository = getCtx().getBean(UserRepository.class);
////        User user =  userRepository.findByUsername(auth.getName());
////
////        return String.valueOf(user.getUserCoins());
////    }
//
////    @PostMapping("/{id}")
////    public String handleSave(@PathVariable int id) {
////
////        //przyjmujemy id usera, updatujemy coinsy i zapisujemy progress uzytkownika
////
////        return "tt";
////    }
//}
