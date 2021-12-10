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


    public static ArrayList<Subtask> getSubtasksByParent(int taskID) {
        ArrayList<Subtask> owned = new ArrayList<Subtask>();
        for (Subtask subtask : subtaskList) {
            if (subtask.getTaskId() == taskID) {
                owned.add(subtask);
            }
        }
    
    return owned;
    }


    public static ArrayList<Subtask> getSubtasksByParentId(int id) {
        ArrayList<Subtask> owned = new ArrayList<Subtask>();

        for (Subtask subtask : subtaskList) {
            if (subtask.getTaskId() == id) {
                owned.add(subtask);
            }
        }

        return owned;
    }


    
}
