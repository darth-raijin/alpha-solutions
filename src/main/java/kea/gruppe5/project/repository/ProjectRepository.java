package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Project;

public class ProjectRepository {
    private static ArrayList<Project> test = new ArrayList<>();

    public static void loadProjects() {
        Project project = new Project("123135", 2.5, "Dick Cheney", "asdf", null, null, true, 1);
        test.add(project);
    }
    
}

