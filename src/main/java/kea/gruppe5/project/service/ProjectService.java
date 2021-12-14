package kea.gruppe5.project.service;



import kea.gruppe5.project.models.Project;
import kea.gruppe5.project.repository.ProjectRepository;


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

    public static int createProject(String name, String description, int personnelNumber, String deadline) {
        String[] deadlineArray = deadline.split("-");
        System.out.println(deadlineArray);
        String formattedDeadline = deadlineArray[2] + "/" + deadlineArray[1]  + "/" + deadlineArray[0];
        // Forsøger at oprette projektet - hvis succesfuldt bliver Project id returnet
        return ProjectRepository.createProject(name, description, personnelNumber, formattedDeadline);
    }

    public static boolean updateProject(String name, String description, int id, String deadline) {
        String[] deadlineArray = deadline.split("-");
        System.out.println(deadlineArray);
        String formattedDeadline = deadlineArray[2] + "/" + deadlineArray[1]  + "/" + deadlineArray[0];
        return ProjectRepository.updateProject(name, description, id, formattedDeadline);
    }

    public static void deleteProject(int parseInt) {
        ProjectRepository.deleteProject(parseInt);
        SubProjectService.deleteSubProject(parseInt);
    }

    public static void calculateTime(int projectId) {
        double time = SubProjectService.getTotalTime(projectId);
        System.out.println("Subproject total time: " + time);
        // Gets passed the sum of time from SubProjects
        ProjectRepository.calculateTime(projectId, time);
    }
}
