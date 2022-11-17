package com.mycompany.springsecurityinactionbook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapPasswordComparisonAuthenticationManagerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    @Bean
    AuthenticationManager ldapAuthenticationManager(BaseLdapPathContextSource contextSource,
                                                    PasswordEncoder passwordEncoder,
                                                    LdapAuthoritiesPopulator authorities) {

        var factory = new LdapPasswordComparisonAuthenticationManagerFactory(contextSource, passwordEncoder);
        factory.setUserDnPatterns("uid={0},ou=people,dc=springsecurityinactionbook,dc=com");
        // factory.setUserDetailsContextMapper(new PersonContextMapper());
        factory.setLdapAuthoritiesPopulator(authorities);
        return factory.createAuthenticationManager();
    }

    @Bean
    public LdapAuthoritiesPopulator configLdapAuthoritiesPopulator(BaseLdapPathContextSource  contextSource){
        String groupSearchBase = "ou=groups,dc=springsecurityinactionbook,dc=com";
        var authorities = new DefaultLdapAuthoritiesPopulator(contextSource, groupSearchBase);
        authorities.setGroupSearchFilter("member={0}");
        // authorities.setGroupRoleAttribute("cn");
        return authorities;
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
