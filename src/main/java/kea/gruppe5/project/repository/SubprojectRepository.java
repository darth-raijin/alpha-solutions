package kea.gruppe5.project.repository;

import java.util.ArrayList;

import kea.gruppe5.project.models.Subproject;

public class SubprojectRepository {
    static ArrayList<Subproject> subprojectList = new ArrayList<Subproject>();

    public static void loadSubprojects() {
        Subproject test = new Subproject(0, "ahla name", "description go brr", 1, false, 0);
        subprojectList.add(test);

    }

    public static ArrayList<Subproject> getSubprojectsByParentId(int parentId) {
        ArrayList<Subproject> result = new ArrayList<Subproject>();
        for (Subproject subproject : subprojectList) {
            if (subproject.getProjectId() == parentId) {
                result.add(subproject);
            }
        }
        System.out.println("Found subprojects by parentid: " + result.size());
        return result;
    }

    public static int createSubproject(String name, String description, String id) {
        // TODO Opret i database 

        // OPret i repo
        Subproject newSubproject = new Subproject(0, name, description, Integer.parseInt(id), false, subprojectList.size() + 1);
        subprojectList.add(newSubproject);

        return newSubproject.getId();
    }

    public static Subproject getSubprojectById(int subprojectID) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subprojectID) {
                return subproject;
            }
        }
        return null;
    }

    public static boolean updateSubproject(String name, String description, int id) {
        // UpDATE I DATABASE MUY IMPORTANT
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == id) {
                subproject.setName(name);
                subproject.setDescription(description);
                System.out.println("Subproject " + id + " is updated");
                return true;
            }
        }
        return false;
    }

    public static int deleteSubproject(int subID) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subID) {
                subprojectList.remove(subproject);
                return subproject.getProjectId();
            }
        }
        return -1;
    }

    public static void updateTime(int subProjectId, double totalTime) {
        for (Subproject subproject : subprojectList) {
            if (subproject.getId() == subProjectId) {
                subproject.setSubprojectTime(totalTime);
            }
        }
    }
} 
