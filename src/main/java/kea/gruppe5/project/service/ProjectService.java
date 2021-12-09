package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.models.Project;
import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.repository.ProjectRepository;
import kea.gruppe5.project.repository.SubprojectRepository;

import java.util.ArrayList;

public class ProjectService {
    
    public static ArrayList<Project> getProjectsByUUID(String uuid) {
        ArrayList<Project> projects = ProjectRepository.getProjectsByUUID(uuid);

        return projects;
    }

    public static Project getProjectById(String projectid) {
        Project p = ProjectRepository.getProjectById(projectid);
        System.out.println("Found project with id: " + p);
        return p;
    }

    public static int createProject(String name, String description, String personnelNumber) {
        // Forsøger at oprette projektet - hvis succesfuldt bliver Project id returnet
        return ProjectRepository.createProject(name, description, personnelNumber);
    }

}
