package kea.gruppe5.project.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.models.User;
import kea.gruppe5.project.repository.UserRepository;
import kea.gruppe5.project.utility.DatabaseConnectionManager;


public class AuthService {
    private AuthService(){

    }

    public static User authenticateUser(String email, String password) {
        // Benytter sig af Database for at validere for at undgå lagring af password i systemet
        Map<String, String> userFetch = DatabaseConnectionManager.getUserByEmail(email);

        if (password == userFetch.get("password")) {
            // Brugerens login er gyldigt
            User currentUser = UserRepository.getByEmail(email);

            if (currentUser == null) {
                // Hvis brugeren ikke skulle eksistere i repository vil den blive hentet fra database med alt tilhængende data
                UserRepository.createUserFromDatabase(userFetch);
            }
            return currentUser;
        }
        return null;
    }

    public static User createUser(String fullName, String email, String password, String address, String city, String postalCode, String phoneNumber) {
        User newUser = UserRepository.addUser(new User(fullName, null, email, password, address, city, postalCode, phoneNumber));



        return newUser;
    }


}
