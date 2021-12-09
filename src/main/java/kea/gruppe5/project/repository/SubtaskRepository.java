package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Subtask;

public class SubtaskRepository {
    static ArrayList<Subtask> subtaskList = new ArrayList<Subtask>();

    public static void removeOwnedSubTasks(int taskID) {
        for (Subtask subtask : subtaskList) {
            if (taskID == subtask.getTaskId()) {
                subtaskList.remove(subtask);
            }
        }
    }

    public static double getTotalTime(int subProjectId) {
        double totalTime = 0;

        for (Subtask subtask : subtaskList) {
            if (subProjectId == subtask.getSubProjectId()) {
                totalTime += subtask.getTime();
            }
        }

        return totalTime;
    }

    
}
