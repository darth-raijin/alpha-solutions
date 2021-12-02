package kea.gruppe5.project.models;

import java.util.ArrayList;

public class Project {
    String personnelNumber;
    double totalTime;
    String name;
    String description;
    ArrayList<User> workersList;
    ArrayList<Subproject> subprojectList;
    Boolean isUpdated;

    public Project(String personnelNumber,
                   double totalTime,
                   String name,
                   String description,
                   ArrayList<User> workersList,
                   ArrayList<Subproject> subprojectList,
                   Boolean isUpdated,
                   int id) {
        this.personnelNumber = personnelNumber;
        this.totalTime = totalTime;
        this.name = name;
        this.description = description;
        this.workersList = workersList;
        this.subprojectList = subprojectList;
        this.isUpdated = isUpdated;
        this.id = id;
    }

    public String getPersonnelNumber(){
        return personnelNumber;
    }
    public void setPersonnelNumber(){
        this.personnelNumber = personnelNumber;
    }
    

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<User> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(ArrayList<User> workersList) {
        this.workersList = workersList;
    }

    public ArrayList<Subproject> getSubprojectList() {
        return subprojectList;
    }

    public void setSubprojectList(ArrayList<Subproject> subprojectList) {
        this.subprojectList = subprojectList;
    }

    public Boolean getUpdated() {
        return isUpdated;
    }

    public void setUpdated(Boolean updated) {
        isUpdated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;





}
