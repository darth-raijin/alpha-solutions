package kea.gruppe5.project.service;
//MM

import kea.gruppe5.project.models.Subtask;
import kea.gruppe5.project.repository.SubtaskRepository;

import java.util.ArrayList;

public class SubtaskService {
    

    public static double getTotalTime(int taskID) {
        ArrayList<Subtask> owned = getSubtasksByParentId(taskID);
        double time = 0;
        for (Subtask subtask : owned) {
            time += subtask.getTime();
        }

        return time;
    }

	public static ArrayList<Subtask> getSubtasksByParentId(int id) {

        return SubtaskRepository.getSubtasksByParentId(id);
	}

    public static int createSubtask(String name, String description, double time, String id) {
        return SubtaskRepository.createSubtask(name, description, time, Integer.parseInt(id));
    }

    public static Subtask getSubtaskById(int parseInt) {
        return SubtaskRepository.getSubtaskById(parseInt);
    }

    public static int updateSubtask(String name, String description, double time, int id) {
        return SubtaskRepository.updateSubtask(name, description, time, id);
    }
}
