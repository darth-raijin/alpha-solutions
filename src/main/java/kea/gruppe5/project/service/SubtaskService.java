package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.repository.SubtaskRepository;

import java.util.ArrayList;

public class SubtaskService {


    public static void removeOwnedSubTasks(int subID) {
        SubtaskRepository.removeOwnedSubTasks(subID);
    }

    public static double getTotalTime(int subProjectId) {
        return SubtaskRepository.getTotalTime(subProjectId);
    }
}
