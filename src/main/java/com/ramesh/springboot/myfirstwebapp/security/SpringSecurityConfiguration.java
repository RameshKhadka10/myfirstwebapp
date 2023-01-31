package com.ramesh.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();

    }

}
