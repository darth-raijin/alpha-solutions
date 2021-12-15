package kea.gruppe5.project.repository;
//JLJ & MM
import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Subtask;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class SubtaskRepository {
    static ArrayList<Subtask> subtaskList = new ArrayList<Subtask>();


    public static void loadSubtasks() {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "SELECT * FROM subtasks";
        PreparedStatement preparedStatement;
        int results = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
     
            ResultSet column =  preparedStatement.executeQuery();
            while(column.next()) {
                Subtask subtask = new Subtask(0, column.getString("name"), column.getString("description"), column.getInt("subtaskID"), column.getInt("taskID"), false);
                subtaskList.add(subtask);
                results++;
            }

            System.out.println("Fetched " + results + "subtasks");

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
        }

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

    public static int createSubtask(String name, String description, double time, Integer taskID) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "INSERT INTO subtasks(name, description, taskID, time) values (?,?,?,?) ";
        PreparedStatement preparedStatement;
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
                subtaskID = column.getInt(1);
                System.out.println("Created column " + subtaskID);
            }

        } catch (SQLException err) {
            System.out.println("Something went wrong creating subtask:" + err.getMessage());
            return -1;
        }
        System.out.println("Subtask created successfully");
        Subtask subtask = new Subtask(time,name,description,subtaskID,taskID, false);
        subtaskList.add(subtask);
        System.out.println("Returning TASK ID " + taskID);
        return taskID;

    }
    public static Subtask getSubtaskById(int parseInt) {
        for (Subtask subtask : subtaskList) {
            if (subtask.getSubtaskId() == parseInt) {
                return subtask;
            }
        }
        return null;
    }


    public static int updateSubtask(String name, String description, double time, int id) {
        // Denne skal returnere int fordi den går til Subproject view
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "UPDATE subtasks set name = ?, description = ? WHERE subtaskID = ?";
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
            System.out.println("Current Subtask: " + subtask.getSubtaskId());
            if (subtask.getSubtaskId() == id) {
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
