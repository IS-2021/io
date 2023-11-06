package com.example.demoio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoIoApplication {

    public static void main(String[] args) {
        User u1 = new User("Marcin1","test1");
        User u2 = new User("Marcin2","test2");
        User u3 = new User("Marcin3","test3");

        ApplicationContext context = SpringApplication.run(DemoIoApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(u1.getPassword());
        String hashedPassword2 = passwordEncoder.encode(u2.getPassword());
        String hashedPassword3 = passwordEncoder.encode(u3.getPassword());
        u1.setPassword(hashedPassword);
        u2.setPassword(hashedPassword2);
        u3.setPassword(hashedPassword3);

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        
        //SpringApplication.run(DemoIoApplication.class, args);
    }


}
