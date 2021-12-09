package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return TaskRepository.getTotalTime(subProjectId);
    }
}
