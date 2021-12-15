package kea.gruppe5.project.repository;
//JLJ & MM
import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Task;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class TaskRepository {
    static ArrayList<Task> taskList = new ArrayList<Task>();


    public static void loadTasks() {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "SELECT * FROM tasks";
        PreparedStatement preparedStatement;
        int results = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr);
    
            ResultSet column =  preparedStatement.executeQuery();
            while(column.next()) {
                Task task = new Task(0, column.getString("name"), column.getString("description") ,column.getInt("subprojectID"), column.getInt("taskID"), false);
                taskList.add(task);
                results++;
            }

            System.out.println("Fetched " + results + "tasks");

        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
        }

    }

    public static ArrayList<Task> getTasksByParentId(int parentId) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getSubProjectId() == parentId) {
                tasks.add(task);
            }
        }
        System.out.println("Found " + tasks.size() + " tasks in subproj: " + parentId);
        return tasks;
    }

    public static void removeOwnedTasks(int subID) {
        for (Task task : taskList) {
            if (task.getSubProjectId() == subID) {
                taskList.remove(task);
            }
        }
    }

    public static void updateTime(int id, double taskTime) {
        for (Task task : taskList) {
            if (task.getSubProjectId() == id) {
                task.setTaskTime(taskTime);
            }
        }
    }
    public static int createTask(String name, String description, Integer subprojectID) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "INSERT INTO tasks(name, description, subprojectID) values (?,?,?) ";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, subprojectID);
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
        System.out.println("Task created successfully");
        Task task = new Task(0,name,description,subprojectID,result,false);
        taskList.add(task);
        return result;

    }


    public static Task getTaskById(int taskID) {
        for (Task task : taskList) {
            if (task.getId() == taskID) {
                return task;
            }
        }
        return null;
    }

    public static boolean updateTask(String name, String description, int taskID) {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insstr = "UPDATE tasks set name = ?, description = ? WHERE taskID = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, taskID);
            preparedStatement.executeUpdate();
            System.out.println("Task updated in database");
        } catch (SQLException err) {
            System.out.println("Something went wrong:" + err.getMessage());
            return false;
        }
        // UpDATE I DATABASE MUY IMPORTANT
        for (Task task : taskList) {
            if (task.getId() == taskID) {
                task.setName(name);
                task.setDescription(description);
                System.out.println("Subproject " + taskID + " is updated");
                return true;
            }
        }
        return false;

    }
    
}
