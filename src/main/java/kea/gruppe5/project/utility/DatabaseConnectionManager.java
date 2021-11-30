package kea.gruppe5.project.utility;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection(){
        if(conn != null){
            return conn;
        }

        try(InputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            url = properties.getProperty("spring.datasource.url");
            username = properties.getProperty("spring.datasource.username");
            password = properties.getProperty("spring.datasource.password");
            System.out.println("Password: " + password +  " Username: " + username + " Url: " + url);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected correctly!");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Map<String, String> getUserByEmail(String email) {


        return null;
    }

}
