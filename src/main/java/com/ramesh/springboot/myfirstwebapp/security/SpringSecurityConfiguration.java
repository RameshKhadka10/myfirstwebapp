package com.ramesh.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = this.createUserDetails("ramesh", "ramesh");
        UserDetails userDetails2 = this.createUserDetails("suresh", "suresh");
        UserDetails userDetails3 = this.createUserDetails("ganesh", "ganesh");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2, userDetails3);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private UserDetails createUserDetails(String username, String password) {
        return User.builder()
                .passwordEncoder(input -> passwordEncoder().encode(input))
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
    }

}
