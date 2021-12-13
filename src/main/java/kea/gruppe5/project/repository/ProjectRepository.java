package kea.gruppe5.project.repository;

import java.sql.*;
import java.util.ArrayList;

import kea.gruppe5.project.models.Project;
import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.utility.DatabaseConnectionManager;

public class ProjectRepository {
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

    private static ArrayList<Project> projectRepository = new ArrayList<>();

    public static void loadProjects() {
        Project project = new Project(0, 0, "Dick Cheney", "Building Dick Cheney clone", null, true, 1);
        projectRepository.add(project);
    }

    public static ArrayList<Project> getProjectsByUUID(String uuid) {
        ArrayList<Project> result = new ArrayList<>();

        for (Project project : projectRepository) {
            if (project.getPersonnelNumber().equals(uuid)) {
                result.add(project);
            }
        }
        System.out.println("User has " + result.size() + " projects");
        return result;
    }

    public static Project getProjectById(String projectid) {
        for (Project project : projectRepository) {
            if (Integer.parseInt(projectid) == project.getId()) {
                System.out.println("Searching for project " + projectid + " found!");
                return project;
            }
        }
        return null;
    }

    public static int createProject(String name, String description, Integer personnelNumber) {
        setConnection();
        String insstr = "INSERT INTO projects(name, description, personnelNumber) values (?,?,?) ";
        PreparedStatement preparedStatement;
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(insstr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name.replace("[", "").replace("]", ""));
            preparedStatement.setString(2, description.replace("[", "").replace("]", ""));
            preparedStatement.setInt(3, personnelNumber);
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
        
        System.out.println("Project created successfully");
        Project project = new Project(personnelNumber, 0, name,description, null, null, result);
        projectRepository.add(project);
        return result;

    }
    

    public static boolean updateProject(String name, String description, int id) {
        for (Project project : projectRepository) {
            if (project.getId() == id) {
                project.setName(name);
                project.setDescription(description);
                System.out.println("Project successfully updated - id: " + project.getId());
                return true;

            }
        }
        return false;
    }

	public static void deleteProject(int parseInt) {
        for (Project project : projectRepository) {
            if (project.getId() == parseInt) {
                projectRepository.remove(project);
            }
        }
	}

    public static void calculateTime(int projectId, double time) {
        for (Project project : projectRepository) {
            if (project.getId() == projectId) {
                project.setTotalTime(time);
                System.out.println("New project time: " + project.getTotalTime());
            }
        }
    }
    
}

