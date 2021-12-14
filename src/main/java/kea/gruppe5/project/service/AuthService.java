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
        String userPassword = DatabaseConnectionManager.getPasswordByEmail(email);

        if (password.equals(userPassword)) {
            // Brugerens login er gyldigt
            User currentUser = UserRepository.getUserByEmail(email);

            if (currentUser == null) {
                // Hvis brugeren ikke skulle eksistere i repository vil den blive hentet fra database med alt tilhængende data
                //UserRepository.createUserFromDatabase(userFetch);
            }
            return currentUser;
        }
        return null;
    }

}
