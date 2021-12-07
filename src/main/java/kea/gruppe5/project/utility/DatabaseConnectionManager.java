package kea.gruppe5.project.utility;

import java.io.*;
import java.sql.*;
import java.util.Map;
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
            username = properties.getProperty("spring.datasource.username");
            password = properties.getProperty("spring.datasource.password");
            System.out.println("Password: " + password + " Username: " + username + " Url: " + url);
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


    public String createProject(String name, String description, int userpersonnelNumber) {
        getConnection();
        String insstr = "INSERT INTO alphasolutions.project(name, description, UserpersonnelNumber) " + "values (?, ?, ?)";
        PreparedStatement preparedStatement;
        String createProjectResult = "";
        try {
            // Result bliver brugt til at skaffe det korrekte ID efter at der bliver indsat
            preparedStatement = getConnection().prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[","").replace("]",""));
            preparedStatement.setString(2, description.replace("[","").replace("]",""));
            // Hvad med UserpersonellNumber?
            preparedStatement.setInt(3, userpersonnelNumber);
            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                createProjectResult = column.getString(1);
                System.out.println("Created column " + createProjectResult);
            }

        } catch (SQLException err) {
            System.out.println("error while creating project:" + err.getMessage());
            return "400";
        }
        System.out.println("Project "+ name+ " created" );
        System.out.println(createProjectResult);
        return createProjectResult;
    }

    public String addsubProject (int id, String name, String description, boolean isCompleted, int projectsId) {
        getConnection();
        String insstr = "INSERT INTO alphasolutions.subprojects(name, description, isCompleted, Projectsid) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        String addsubProjectResult = "";
        try {
            // Result bliver brugt til at skaffe det korrekte ID efter at der bliver indsat
            preparedStatement = getConnection().prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBoolean(3, isCompleted);
            //Skal denne være der?
            preparedStatement.setInt(4, projectsId);
            System.out.println(insstr);
            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                addsubProjectResult = column.getString(1);
                System.out.println("Created column " + addsubProjectResult);

            }

        } catch (SQLException err) {
            System.out.println("error while updating project:" + err.getMessage());
            return "400";
        }
        System.out.println(name + " updated");
        System.out.println(addsubProjectResult);
        return "200";
    }

    public String createTask(String name, String description, double time) {
        getConnection();
        String insstr = "INSERT INTO alphasolutions.tasks(name, description, time) " + "values (?, ?, ?)";
        PreparedStatement preparedStatement;
        String createTaskResult = "";
        try {
            // Result bliver brugt til at skaffe det korrekte ID efter at der bliver indsat
            preparedStatement = getConnection().prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            //Skal vi også lave med id? man kan ikke bruge replace til int
            preparedStatement.setString(1, name.replace("[","").replace("]",""));
            preparedStatement.setString(2, description.replace("[","").replace("]",""));
            //Skal time med?

            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                createTaskResult = column.getString(1);
                System.out.println("Created column " + createTaskResult);
            }

        } catch (SQLException err) {
            System.out.println("error while creating task:" + err.getMessage());
            return "400";
        }
        System.out.println("Task " + name + "created");
        System.out.println(createTaskResult);
        return createTaskResult;
    }

    public String addSubtask (int id, String name, String description, boolean isCompleted, int taskId) {
        getConnection();
        String insstr = "INSERT INTO alphasolutions.subtask(name, description, isCompleted, taskId) values (?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        String result = "";
        try {
            // Result bliver brugt til at skaffe det korrekte ID efter at der bliver indsat
            preparedStatement = getConnection().prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBoolean(3, isCompleted);
            //Skal denne være der?
            preparedStatement.setInt(4, taskId);
            System.out.println(insstr);
            preparedStatement.executeUpdate();

            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getString(1);
                System.out.println("Created column " + result);

            }

        } catch (SQLException err) {
            System.out.println("error while updating task:" + err.getMessage());
            return "400";
        }
        System.out.println("Task" + name + " updated");
        System.out.println(result);
        return "200";
    }
}
