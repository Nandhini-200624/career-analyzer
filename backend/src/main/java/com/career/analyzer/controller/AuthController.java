package com.career.analyzer.controller;

import com.career.analyzer.dto.LoginRequest;
import com.career.analyzer.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        return JwtUtil.generateToken(
                request.getEmail());
    }
}