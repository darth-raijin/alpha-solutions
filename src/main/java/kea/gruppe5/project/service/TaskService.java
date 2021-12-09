package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.models.Task;
import kea.gruppe5.project.repository.TaskRepository;

import java.util.ArrayList;

public class TaskService {
    @PostMapping ("/updatetask")
    public void updateTask (@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam ArrayList<Integer> assignedWorkers,
                            @RequestParam double time) {

    }
    @PostMapping ("/createtask")
    public void createTask (@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam ArrayList<Integer> assignedWorkers,
                            @RequestParam double time) {

    }
    public static ArrayList<Task> getTasksByParentId(int parentId) {
        return TaskRepository.getTasksByParentId(parentId);

    }
}
