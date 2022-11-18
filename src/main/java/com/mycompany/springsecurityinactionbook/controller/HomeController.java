package com.mycompany.springsecurityinactionbook.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Map<String, Object> get() throws ExecutionException, InterruptedException {

        Callable<String>  task = () -> SecurityContextHolder.getContext().getAuthentication().getName();
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            return Map.of(
                    "page", "home Page :" + executorService.submit(
                            new DelegatingSecurityContextCallable<>(task)
                    ).get()
            );

        } finally {
              executorService.shutdown();
        }
    }
}
