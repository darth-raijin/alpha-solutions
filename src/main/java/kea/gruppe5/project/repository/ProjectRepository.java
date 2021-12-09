package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Project;

public class ProjectRepository {
    private static ArrayList<Project> projectRepository = new ArrayList<>();

    public static void loadProjects() {
        Project project = new Project("test0101k", 0, "Dick Cheney", "Building Dick Cheney clone", null, true, 1);
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
        Project project = new Project(personnelNumber, 0, name, description, null, false, projectRepository.size() + 1);
        projectRepository.add(project);

        System.out.println("Project created with id: " + project.getId());
        return project.getId();

        // IF ALSO GUCCI RETURN PROJEECT ID

        // IF NOT -1

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

