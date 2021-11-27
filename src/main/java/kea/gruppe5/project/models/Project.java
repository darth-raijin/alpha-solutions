package kea.gruppe5.project.models;

public class Project {
    String managerID;
    double totalTime;
    String name;
    String description;
    String workersList;
    String subprojectList;
    Boolean isUpdated;

    public Project(String managerID,
                   double totalTime,
                   String name,
                   String description,
                   String workersList,
                   String subprojectList,
                   Boolean isUpdated,
                   int id) {
        this.managerID = managerID;
        this.totalTime = totalTime;
        this.name = name;
        this.description = description;
        this.workersList = workersList;
        this.subprojectList = subprojectList;
        this.isUpdated = isUpdated;
        this.id = id;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
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

    public String getWorkersList() {
        return workersList;
    }

    public void setWorkersList(String workersList) {
        this.workersList = workersList;
    }

    public String getSubprojectList() {
        return subprojectList;
    }

    public void setSubprojectList(String subprojectList) {
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
