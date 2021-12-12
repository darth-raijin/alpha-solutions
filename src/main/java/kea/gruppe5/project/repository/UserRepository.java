package kea.gruppe5.project.repository;

import java.util.ArrayList;
import java.util.Map;

import kea.gruppe5.project.models.User;

public class UserRepository {
    static ArrayList<User> users = new ArrayList<>();

    private UserRepository(){}


    public static void loadUsers() {
        User testUser = new User("John Doe", "test0101k", "test@vontest.com");
        users.add(testUser);

        // Database load alle brugere bla bla bla
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


    public static User addUser(String fullName, String email, String password) {
        return null;
    }


    public static void createUserFromDatabase(Map<String, String> userFetch) {
    }

    
}
