package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class SubprojectRepository {
    static ArrayList<Subproject> subprojectList = new ArrayList<Subproject>();

    private static Connection connection = null;

    public static boolean setConnection() {
        final String url = DatabaseConnectionManager.getUrl(); // TODO FIX LOGIN
        boolean res = false;
        try {
            connection = DriverManager.getConnection(url, DatabaseConnectionManager.getUsername(), DatabaseConnectionManager.getPassword());
            res = true;
            System.out.println("Connection made!");
        } catch (SQLException ioerr) {
            System.out.println(ioerr);
            throw new RuntimeException(ioerr);
        }
        return res;
    }

    public static void loadSubprojects() {
        Subproject test = new Subproject(0, "ahla name", "description go brr", 1, false, 0);
        subprojectList.add(test);

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

    public static String createSubproject(String name, String description) {
        setConnection();
        String insstr = "INSERT INTO subprojects(name, description) values (?,?) ";
        PreparedStatement preparedStatement;
        String result = "";
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.executeUpdate();
            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getString(1);
                System.out.println("Created column " + result);
            }

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
            return "400";
        }
        System.out.println("Wishlist created successfully");
        return result;

    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Opret i database 

        // OPret i repo
        Subproject newSubproject = new Subproject(0, name, description, Integer.parseInt(id), false, subprojectList.size() + 1);
        subprojectList.add(newSubproject);

        return newSubproject.getId();
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
