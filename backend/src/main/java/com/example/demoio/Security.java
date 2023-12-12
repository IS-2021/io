package com.example.demoio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class Security {

    @Autowired
    private CustomUserDetails userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(Arrays.asList("http://localhost:*"));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
                    configuration.setAllowCredentials(true);

                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", configuration);
                    httpSecurityCorsConfigurer.configurationSource(source);
                })
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/images/**", "/login", "/register").permitAll() // Permit access to static resources and login page
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                        .invalidateHttpSession(true)
                )
                .csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .ignoringRequestMatchers("/api/**")
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
