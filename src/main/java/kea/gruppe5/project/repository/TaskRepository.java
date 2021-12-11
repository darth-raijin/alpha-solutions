package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Task;

public class TaskRepository {
    static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void loadTasks() {
        taskList.add(new Task(0, "Task Name", "Task Disc", 0, 0, false));
        taskList.add(new Task(0, "Task Name", "Task Disc", 0, 1, false));
    }

    public static ArrayList<Task> getTasksByParentId(int parentId) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getSubProjectId() == parentId) {
                tasks.add(task);
            }
        }
        System.out.println("Found " + tasks.size() + " tasks in subproj: " + parentId);
        return tasks;
    }
    
}
