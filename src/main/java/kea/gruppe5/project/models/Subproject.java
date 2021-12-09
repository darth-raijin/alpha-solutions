package kea.gruppe5.project.models;

public class Subproject {
    double subprojectTime;
    String name;
    String description;
    int projectId;
    boolean isCompleted;
    int id;

    public Subproject(double subprojectTime,
                      String name,
                      String description,
                      int projectId,
                      boolean isCompleted,
                      int id) {
        this.subprojectTime = subprojectTime;
        this.name = name;
        this.description = description;
        this.projectId = projectId;
        this.isCompleted = isCompleted;
        this.id = id;
    }


    public double getSubprojectTime() {
        return subprojectTime;
    }

    public void setSubprojectTime(double subprojectTime) {
        this.subprojectTime = subprojectTime;
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


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
