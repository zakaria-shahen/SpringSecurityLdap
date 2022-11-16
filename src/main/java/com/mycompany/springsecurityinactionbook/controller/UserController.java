package com.mycompany.springsecurityinactionbook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> get(Authentication authentication) {
        return Map.of(
                "page", "User Page",
                "access", "user-only",
                "you", authentication.getAuthorities()
        );
    }}
