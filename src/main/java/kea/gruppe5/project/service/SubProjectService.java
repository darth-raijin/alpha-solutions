package kea.gruppe5.project.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kea.gruppe5.project.models.Subproject;
import kea.gruppe5.project.repository.SubprojectRepository;

import java.util.ArrayList;

public class SubProjectService {

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        return SubprojectRepository.getSubprojectsByParentId(parentId);
    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Efter oprettelse af SP, skal Id returneres for at redirect
        int createdSub = SubprojectRepository.createSubproject(name, description, id);

        if(createdSub >= 0) {
            System.out.println("Subproject created - ID: " + createdSub);
        } else {
            System.out.println("Failed to create subproject");
        }
        System.out.println("Subproject created - ID: " + createdSub);

        return createdSub;
    }
}
