package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import kea.gruppe5.project.models.User;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class UserRepository {
    private static Connection connection = null;


    static ArrayList<User> users = new ArrayList<>();

    private UserRepository() {
    }


    public static void loadUsers() {
        //User testUser = new User("John Doe", "test0101k", "test@vontest.com");
        //users.add(testUser);

        // Database load alle brugere bla bla bla
    }


    public static User getUserByEmail(String email) {
        User user = new User();
        Connection connection = DatabaseConnectionManager.getConnection();
        String getStr = "SELECT name, email, address, postalcode, country, phonenumber, city, " +
                " personnelNumber FROM alphasolutions.users WHERE email = '" + email + "'";
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


    public static String addUser(User newUser) {
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
        String result = "";
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newUser.getName().replace("[", "").replace("]", ""));
            preparedStatement.setString(2, newUser.getEmail().replace("[", "").replace("]", ""));
            preparedStatement.setString(3, newUser.getAddress().replace("[", "").replace("]", ""));
            preparedStatement.setString(4, newUser.getPostalCode().replace("[", "").replace("]", ""));
            preparedStatement.setString(5, newUser.getCountry().replace("[", "").replace("]", ""));
            preparedStatement.setString(6, newUser.getPhoneNumber().replace("[", "").replace("]", ""));
            preparedStatement.setString(7, newUser.getPassword().replace("[", "").replace("]", ""));
            preparedStatement.setString(8, newUser.getCity().replace("[", "").replace("]", ""));

            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getString(1);
                System.out.println("Created column " + result);
            }

        } catch (SQLException err) {
            System.out.println("bad happened:" + err.getMessage());
            return "400";

        }


        //connection.close();


        //public static void createUserFromDatabase(Map<String, String> userFetch) {}
        return "200";

    }
}