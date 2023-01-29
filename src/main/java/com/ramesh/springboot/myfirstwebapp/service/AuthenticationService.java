package com.ramesh.springboot.myfirstwebapp.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password){
        return username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin");
    }
}
