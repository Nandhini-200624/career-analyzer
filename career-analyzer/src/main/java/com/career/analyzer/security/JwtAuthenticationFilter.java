
package com.career.analyzer.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

            if (!JwtUtil.validateToken(token)) {

                filterChain.doFilter(request,response);

                return;
            }

            String username =
                    JwtUtil.extractUsername(token);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            Collections.emptyList());

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            System.out.println("Authenticated User = " + username);
        }

        filterChain.doFilter(request, response);
    }
}