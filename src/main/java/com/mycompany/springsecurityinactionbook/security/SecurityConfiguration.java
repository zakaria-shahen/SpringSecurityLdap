package com.mycompany.springsecurityinactionbook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService configUserManager(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .passwordEncoder(passwordEncoder::encode)
                        .build(),
                User.builder()
                        .username("admin")
                        .password("admin")
                        .passwordEncoder(passwordEncoder::encode)
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder configPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain configAdminAccessPages(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                 .mvcMatcher("/admin")
                 .authorizeHttpRequests().anyRequest()
                 .hasRole("ADMIN")
                 .and().httpBasic();

        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain configUserAccessPages(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .mvcMatcher("/user")
                .authorizeHttpRequests().anyRequest()
                .hasAnyRole("USER", "ADMIN")
                .and().httpBasic();

        return httpSecurity.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain configAnonymousAccessPages(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .mvcMatcher("/home")
                .anonymous();

        return httpSecurity.build();
    }

}
