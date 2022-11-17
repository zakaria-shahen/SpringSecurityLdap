package com.mycompany.springsecurityinactionbook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityInActionBookApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.matches("admin", passwordEncoder.encode("admin")));
    }

}
