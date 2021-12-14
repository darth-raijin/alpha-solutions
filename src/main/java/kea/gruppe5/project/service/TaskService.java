package kea.gruppe5.project.service;


import kea.gruppe5.project.models.Task;
import kea.gruppe5.project.repository.TaskRepository;

import java.util.ArrayList;

public class TaskService {

    public static ArrayList<Task> getTasksByParentId(int parentId) {
        return TaskRepository.getTasksByParentId(parentId);

    }
    public static void removeOwnedTasks(int subID) {
        TaskRepository.removeOwnedTasks(subID);
        SubtaskService.removeOwnedSubTasks(subID);
    }
    public static double getTotalTime(int subProjectId) {
        ArrayList<Task> tasks = TaskRepository.getTasksByParentId(subProjectId);
        double totalTime = 0;
        for (Task task : tasks) {
            // Dybde først søgning fortsætter. Hvert task får udregnet sin egen samlede tid baseret på subtasks
            double taskTime = SubtaskService.getTotalTime(task.getId());
            TaskRepository.updateTime(task.getId(), taskTime);
            System.out.println("Task " + task.getId() + " has total time " + taskTime);
            totalTime += taskTime;
        }

        return totalTime;
    }
	public static int createTask(String name, String description, String id) {
        return TaskRepository.createTask(name, description, Integer.parseInt(id));
	
	}
    public static Task getTaskById(int parseInt) {
        return TaskRepository.getTaskById(parseInt);
    }
    public static boolean updateTask(String name, String description, int parseInt) {
        return TaskRepository.updateTask(name, description, parseInt);
    }
}
