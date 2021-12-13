package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Subtask;
import kea.gruppe5.project.models.Task;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class SubtaskRepository {
    static ArrayList<Subtask> subtaskList = new ArrayList<Subtask>();

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

    public static void removeOwnedSubTasks(int taskID) {
        for (Subtask subtask : subtaskList) {
            if (taskID == subtask.getTaskId()) {
                subtaskList.remove(subtask);
            }
        }
    }


    public static ArrayList<Subtask> getSubtasksByParent(int taskID) {
        ArrayList<Subtask> owned = new ArrayList<Subtask>();
        for (Subtask subtask : subtaskList) {
            if (subtask.getTaskId() == taskID) {
                owned.add(subtask);
            }
        }
    
    return owned;
    }


    public static ArrayList<Subtask> getSubtasksByParentId(int id) {
        ArrayList<Subtask> owned = new ArrayList<Subtask>();

        for (Subtask subtask : subtaskList) {
            if (subtask.getTaskId() == id) {
                owned.add(subtask);
            }
        }

        return owned;
    }

    public static int createSubtask(String name, String description, Integer taskID, double time) {
        setConnection();
        String insstr = "INSERT INTO subtasks(name, description, taskID, time) values (?,?,?,?) ";
        PreparedStatement preparedStatement;
        int result = 0;
        int subtaskID = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, taskID);
            preparedStatement.setDouble(4, time);
            preparedStatement.executeUpdate();
            ResultSet column = preparedStatement.getGeneratedKeys();
            if (column.next()) {
                result = column.getInt("taskID");
                subtaskID = column.getInt(1);
                System.out.println("Created column " + result);
            }

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
            return -1;
        }
        System.out.println("Subtask created successfully");
        Subtask subtask = new Subtask(time,name,description,subtaskID,result,false);
        return result;

    }
    public static Subtask getSubtaskById(int parseInt) {
        for (Subtask subtask : subtaskList) {
            if (subtask.getId() == parseInt) {
                return subtask;
            }
        }
        return null;
    }


    public static int updateSubtask(String name, String description, double time, int id) {
        // Denne skal returnere int fordi den går til Subproject view
        setConnection();
        String insstr = "UPDATE subtasks set name = ?, description = ? WHERE id = ?";
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
            return -1;
        }
        System.out.println("Incoming id: " + id);
        for (Subtask subtask : subtaskList) {
            System.out.println("Current Subtask: " + subtask.getId());
            if (subtask.getId() == id) {
                subtask.setDescription(description);
                subtask.setName(name);
                subtask.setTime(time);
                System.out.println("owned " + subtask.getTaskId() + "");
                return subtask.getTaskId();
            }
        }
        return -1;
    }


    
}
