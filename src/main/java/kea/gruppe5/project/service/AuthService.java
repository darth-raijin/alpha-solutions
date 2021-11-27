package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;


public class AuthService {
    private AuthService(){}

    public static String authenticateUser(String email, String password) {
        // TODO tjek user repository om email eksisterer, krydstjek password
        return "Yes";
    }


}