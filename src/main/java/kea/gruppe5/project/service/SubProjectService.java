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
}
