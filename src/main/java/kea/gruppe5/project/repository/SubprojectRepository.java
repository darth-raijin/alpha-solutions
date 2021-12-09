package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Subproject;

public class SubprojectRepository {
    static ArrayList<Subproject> subprojectList = new ArrayList<Subproject>();

    public static void loadSubprojects() {
        Subproject test = new Subproject(0, "ahla name", "description go brr", 1, false, 0);
        subprojectList.add(test);
        subprojectList.add(test);

        subprojectList.add(test);
    }

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        ArrayList<Subproject> result = new ArrayList<Subproject>();
        for (Subproject subproject : subprojectList) {
            if (subproject.getProjectId() == parentId) {
                result.add(subproject);
            }
        }
        System.out.println("Found: " + result.size());
        return result;
    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Opret i database 

        // OPret i repo
        Subproject newSubproject = new Subproject(0, name, description, Integer.parseInt(id), false, subprojectList.size() + 1);
        subprojectList.add(newSubproject);

        return newSubproject.getId();
    }
} 
