package com.career.analyzer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig {

    @Bean

    public SecurityFilterChain filterChain(
            HttpSecurity http) {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/**")

                        .permitAll()

                        .anyRequest()

                        .permitAll())

                .addFilterBefore(
                        jwtFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public JwtAuthenticationFilter jwtFilter() {
        return new JwtAuthenticationFilter();
    }
}