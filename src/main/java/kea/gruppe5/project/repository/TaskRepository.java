package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Task;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class TaskRepository {
    static ArrayList<Task> taskList = new ArrayList<Task>();

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

    public static void loadTasks() {
        taskList.add(new Task(10, "Task Name", "Task Disc", 0, 0, false));
        taskList.add(new Task(5, "Task Name", "Task Disc", 0, 1, false));
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
    public static String createTask(String name, String description) {
        setConnection();
        String insstr = "INSERT INTO tasks(name, description) values (?,?) ";
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


    public static int createTask(String name, String description, int id) {
        Task newTask = new Task(name, description, id);

        // TODO opret i database - opdater newTask med ID fra DB
        newTask.setId(taskList.size() + 1);
        // Opret i Repository
        taskList.add(newTask);
        return newTask.getId();
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
        setConnection();
        String insstr = "UPDATE tasks set name = ?, description = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, taskID);
            preparedStatement.executeUpdate();

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
