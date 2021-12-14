package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class SubprojectRepository {
    static ArrayList<Subproject> subprojectList = new ArrayList<Subproject>();

    private static Connection connection = null;


    public static void loadSubprojects() {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "SELECT * FROM subprojects";
        PreparedStatement preparedStatement;
        int results = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
     
            ResultSet column =  preparedStatement.executeQuery();
            while(column.next()) {
                Subproject p = new Subproject(0, column.getString("name"), column.getString("description"), column.getInt("projectID"), false, column.getInt("subprojectID"));
                subprojectList.add(p);
                results++;
            }

            System.out.println("Fetched " + results + "subprojects");

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
        }


    }

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        ArrayList<Subproject> result = new ArrayList<Subproject>();
        for (Subproject subproject : subprojectList) {
            if (subproject.getProjectId() == parentId) {
                result.add(subproject);
            }
        }
        System.out.println("Found subprojects by parentid: " + result.size());
        return result;
    }

    public static int createSubproject(String name, String description, Integer projectID) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "INSERT INTO subprojects(name, description, projectsID) values (?,?,?) ";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, projectID);
            preparedStatement.executeUpdate();
            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getInt(1);
                System.out.println("Created column " + result);
            }

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
            return -1;
        }
        System.out.println("Subproject created successfully");
        Subproject newSubproject = new Subproject(0, name, description, projectID, false, result);
        subprojectList.add(newSubproject);

        return result;

    }

    public static Subproject getSubprojectById(int subprojectID) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subprojectID) {
                return subproject;
            }
        }
        return null;
    }

    public static boolean updateSubproject(String name, String description, int id) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "UPDATE subprojects set name = ?, description = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Task updated in database");
        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
            return false;
        }
        // UpDATE I DATABASE MUY IMPORTANT
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == id) {
                subproject.setName(name);
                subproject.setDescription(description);
                System.out.println("Subproject " + id + " is updated");
                return true;
            }
        }
        return false;
    }

    public static int deleteSubproject(int subID) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subID) {
                subprojectList.remove(subproject);
                return subproject.getProjectId();
            }
        }
        return -1;
    }

    public static void updateTime(int subProjectId, double totalTime) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subProjectId) {
                subproject.setSubprojectTime(totalTime);
            }
        }
    }
} 
