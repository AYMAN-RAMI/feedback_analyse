package com.feedback.analyse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())                        // Désactive la protection CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()                        // Autorise toutes les requêtes
                )
                .sessionManagement(session -> session.disable());     // Pas de gestion de session

        return http.build();
    }
}
