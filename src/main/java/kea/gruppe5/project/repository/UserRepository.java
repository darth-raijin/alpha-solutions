package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.User;

public class UserRepository {

    private UserRepository(){}

    static ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {

    }

    public static User getByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    public void updateUser(User user) {

    }

    
}
