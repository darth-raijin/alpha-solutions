package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.User;

public class UserRepository {
    static ArrayList<User> users = new ArrayList<>();

    private UserRepository(){}


    public static void loadUsers() {
        User testUser = new User("John Doe", "test0101k", "test@vontest.com", null, null, null, null, null);
        users.add(testUser);

        // Database load alle brugere bla bla bla
    }

    public static User addUser(User user) {
        // Opret bruger i database, tilføj personnelNumber til user objekt og returner

        return user;
    }

    public static User getByEmail(String email) {
        System.out.println("Looking for user " + email);

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Found user!");
                return user;
            }
        }

        return null;
    }

    public void updateUser(User user) {

    }

    
}
