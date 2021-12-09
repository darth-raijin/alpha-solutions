package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Task;

public class TaskRepository {
    static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void loadTasks() {
        taskList.add(new Task(10, "Task Name", "Task Disc", 0, 0, false));
        taskList.add(new Task(5, "Task Name", "Task Disc", 0, 1, false));
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

    public static void removeOwnedTasks(int subID) {
        for (Task task : taskList) {
            if (task.getSubProjectId() == subID) {
                taskList.remove(task);
            }
        }
    }

    public static double getTotalTime(int subProjectId) {
        double totalTime = 0;
        for (Task task : taskList) {
            if (task.getSubProjectId() == subProjectId) {
                totalTime += task.getTaskTime();
            }
        }
        System.out.println("All tasks for subproject " + subProjectId + " take " + totalTime);
        return totalTime;
    }
    
}
