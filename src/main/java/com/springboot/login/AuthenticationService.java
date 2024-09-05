package com.springboot.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password) {
        return username.equalsIgnoreCase("learn")
                && password.equalsIgnoreCase("java");

    }
}
