package kea.gruppe5.project.utility;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager() {
    }


    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        }

        try (InputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            url = properties.getProperty("spring.datasource.url");
            username = properties.getProperty("spring.datasource.password");
            password = properties.getProperty("spring.datasource.username");
            System.out.println("Password: " + password + " Username: " + username + " Url: " + url);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected correctly!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getPasswordByEmail(String email) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String getStr = "SELECT password FROM users WHERE email = '" + email + "'";
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

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DatabaseConnectionManager.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DatabaseConnectionManager.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DatabaseConnectionManager.password = password;
    }

}
