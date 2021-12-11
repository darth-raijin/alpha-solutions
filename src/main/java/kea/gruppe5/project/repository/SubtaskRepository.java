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


    public static int createSubtask(String name, String description, double time, String id) {
        // TODO Add to Database


        // TODO add til repo
        Subtask subtask = new Subtask(time, name, description, subtaskList.size() + 1, Integer.parseInt(id), false);
        subtaskList.add(subtask);
        return subtask.getId();
    }


    public static Subtask getSubtaskById(int parseInt) {
        for (Subtask subtask : subtaskList) {
            if (subtask.getId() == parseInt) {
                return subtask;
            }
        }
        return null;
    }


    public static int updateSubtask(String name, String description, double time, int id) {
        System.out.println("Incoming id: " + id);
        for (Subtask subtask : subtaskList) {
            System.out.println("Current Subtask: " + subtask.getId());
            if (subtask.getId() == id) {
                subtask.setDescription(description);
                subtask.setName(name);
                subtask.setTime(time);
                System.out.println("owned " + subtask.getTaskId() + "");
                return subtask.getTaskId();
            }
        }
        return -1;
    }


    
}
