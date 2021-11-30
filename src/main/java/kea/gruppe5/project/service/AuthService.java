package kea.gruppe5.project.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.models.User;
import kea.gruppe5.project.repository.UserRepository;
import kea.gruppe5.project.utility.DatabaseConnectionManager;


public class AuthService {
    private String testEmail = "email@test.com";
    private String testPassword = "password";
    private boolean testing = true;

    private AuthService(){

    }


    public static User authenticateUser(String email, String password) {
        // Benytter sig af Database for at validere for at undgå lagring af password i systemet
        Map<String, String> userFetch = DatabaseConnectionManager.getUserByEmail(email);

        if (password == userFetch.get("password")) {
            // Brugerens login er gyldigt
            User currentUser = UserRepository.getByEmail(email);
            if (currentUser == null) {
                // Fetch by database and load repository
            }

            
        }

        return null;
    }


}