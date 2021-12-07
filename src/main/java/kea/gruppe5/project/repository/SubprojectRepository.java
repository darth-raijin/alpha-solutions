package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Subproject;

public class SubprojectRepository {
    static ArrayList<Subproject> subprojectList = new ArrayList<Subproject>();

    public static void loadSubprojects() {
        Subproject test = new Subproject(0, null, null, null, 0, false, 0);
    }

    public static ArrayList<Subproject> getSubprojectByParentId(int parentId) {
        ArrayList<Subproject> result = new ArrayList<Subproject>();
        for (Subproject subproject : subprojectList) {
            if (subproject.getProjectId() == parentId) {
                result.add(subproject);
            }
        }
        return result;
    }
} 
