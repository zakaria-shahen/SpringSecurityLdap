package com.mycompany.springsecurityinactionbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Map<String, Object> get() {
        return Map.of(
                "page", "home Page"
        );
    }
}
