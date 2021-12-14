package kea.gruppe5.project.service;


import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.repository.SubprojectRepository;

import java.util.ArrayList;

public class SubProjectService {

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        return SubprojectRepository.getSubprojectsByParentId(parentId);
    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Efter oprettelse af SP, skal Id returneres for at redirect
        int createdSub = SubprojectRepository.createSubproject(name, description, Integer.parseInt(id));

        if(createdSub >= 0) {
            System.out.println("Subproject created - ID: " + createdSub);
        } else {
            System.out.println("Failed to create subproject");
        }
        System.out.println("Subproject created - ID: " + createdSub);

        return createdSub;
    }

    public static Subproject getSubProjectById(int subprojectID) {
        return SubprojectRepository.getSubprojectById(subprojectID);
    }

    public static boolean updateSubProject(String name, String description, int id) {
        return SubprojectRepository.updateSubproject(name, description, id);
    }

    public static double getTotalTime(int projectId) {
        // Fetches all subprojects that belong to project. For each Subproject, total time will be calculated for Task and Subtasks, and set to Subproject time
        ArrayList<Subproject> subprojects = SubprojectRepository.getSubprojectsByParentId(projectId);
        double totalTime = 0;

        for (Subproject subproject : subprojects) {
            // A depth first approach is taken to calculate the time
            double subprojectTime = TaskService.getTotalTime(subproject.getId());
            System.out.println("Subproject ID: " + subproject.getId() + " has time: " + subprojectTime);
            totalTime += subprojectTime;

            SubprojectRepository.updateTime(subproject.getId(), totalTime);
        }
        

        return totalTime;
    }

    public static int deleteSubProject(int subID) {
        SubprojectRepository.deleteSubproject(subID);
        TaskService.removeOwnedTasks(subID);
        return SubprojectRepository.deleteSubproject(subID);
    }
}
