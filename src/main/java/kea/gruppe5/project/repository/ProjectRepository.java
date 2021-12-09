package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Project;

public class ProjectRepository {
    private static ArrayList<Project> projectRepository = new ArrayList<>();

    public static void loadProjects() {
        Project project = new Project("test0101k", 2.5, "Dick Cheney", "Building Dick Cheney clone", null, null, true, 1);
        projectRepository.add(project);
    }

    public static ArrayList<Project> getProjectsByUUID (String uuid) {
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

    public static int createProject(String name, String description, String personnelNumber) {
        System.out.println("Attempting to create project " + name + " by user " + personnelNumber);
        // TODO OPRET FØRST I DATABASE 

        // IF GUCCI OPRET I REPOSITORY
        Project project = new Project(personnelNumber, 2.5, name, description, null, null, false, projectRepository.size() + 1);
        projectRepository.add(project);
        
        System.out.println("Project created with id: " + project.getId());
        return project.getId();

        // IF ALSO GUCCI RETURN PROJEECT ID

        // IF NOT -1

    }
    
}

