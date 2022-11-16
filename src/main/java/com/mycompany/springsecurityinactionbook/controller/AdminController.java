package com.mycompany.springsecurityinactionbook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public Map<String, Object> get(Authentication authentication) {
        return Map.of(
                "page", "home Page",
                "access", "admin-only",
                "you", authentication.getAuthorities()
        );
    }
}
