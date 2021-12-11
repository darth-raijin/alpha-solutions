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
    private static boolean testing = true;

    private AuthService(){

    }


    public static User authenticateUser(String email, String password, HttpSession session) {
        if(testing == true){
            User currentUser = UserRepository.getByEmail("test@vontest.com");
            return currentUser;
        }

        // Benytter sig af Database for at validere for at undgå lagring af password i systemet
        Map<String, String> userFetch = DatabaseConnectionManager.getUserByEmail(email);

        if (password == userFetch.get("password")) {
            // Brugerens login er gyldigt
            User currentUser = UserRepository.getByEmail(email);

            if (currentUser == null) {
                // Hvis brugeren ikke skulle eksistere i repository vil den blive hentet fra database med alt
                // tilhængende data
            }
            return currentUser;
        }

        return null;
    }

    public static User createUser(String fullName, String email, String password, String address, String city, String postalCode, String phoneNumber) {
        User newUser = UserRepository.addUser(new User(fullName, null, email, password, address, city, postalCode, phoneNumber, password));



        return newUser;
    }


}