package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.User;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class UserRepository {


    static ArrayList<User> users = new ArrayList<>();

    private UserRepository() {
    }


    public static void loadUsers() {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "SELECT * FROM users";
        PreparedStatement preparedStatement;
        int results = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
     
            ResultSet column =  preparedStatement.executeQuery();
            while(column.next()) {
                User u = new User(column.getString("name"), column.getString("email"), column.getInt("personnelNumber"));
                users.add(u);
                results++;
            }

            System.out.println("Fetched " + results + " users");

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
        }  


    }
    public static User getUserByEmail(String email) {
        User user = new User();
        Connection connection = DatabaseConnectionManager.getConnection();
        String getStr = "SELECT name, email, address, postalcode, country, phonenumber, city, " +
                " personnelNumber FROM users WHERE email = '" + email + "'";
        Statement statement;

        try {
            statement = connection.createStatement();

            ResultSet values = statement.executeQuery(getStr);
            if (values.next()) {
                user.setName(values.getString("name"));
                user.setEmail(values.getString("email"));
                user.setAddress(values.getString("address"));
                user.setPostalCode(values.getString("postalCode"));
                user.setCountry(values.getString("country"));
                user.setPhoneNumber(values.getString("phoneNumber"));
                user.setCity(values.getString("city"));
                user.setPersonnelNumber(values.getInt("personnelNumber"));
            } else {
                user = null;
            }
        } catch (SQLException err) {
            System.out.println("bad happened:" + err.getMessage());
            return null;

        }

        return user;
    }


    public static String getPasswordByEmail(String email) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String getStr = "SELECT password FROM alphasolutions.users WHERE email = '"+ email + "'";
        Statement statement;
        String passwordResult = "";

        try {
            statement = connection.createStatement();

            ResultSet column = statement.executeQuery(getStr);
            if (column.next()) {
                passwordResult = column.getString(1);
            }

        } catch (SQLException err) {
            System.out.println("bad happened:" + err.getMessage());
            return " ";

        }

        return passwordResult;
    }


    public static User addUser(String fullName, String email, String password, String address, String city,
                                 String postalCode, String phoneNumber, String country) {
        Connection connection = DatabaseConnectionManager.getConnection();

        String insstr = "INSERT INTO users (" +
                "name, " +
                "email, " +
                "address, " +
                "postalCode, " +
                "country, " +
                "phoneNumber, " +
                "password, " +
                "city) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, postalCode);
            preparedStatement.setString(5, country);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setString(7, password);
            preparedStatement.setString(8, city);

            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getInt(1);
                System.out.println("Created column " + result);
            }

        } catch (SQLException err) {
            System.out.println("bad happened:" + err.getMessage());
            return null;

        }

        User newUser = new User(fullName, email, address, city, postalCode, phoneNumber, country);
        newUser.setPersonnelNumber(result);

        users.add(newUser);

        return newUser;

    }
}