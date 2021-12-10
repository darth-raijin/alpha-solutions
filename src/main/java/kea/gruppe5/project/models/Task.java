package kea.gruppe5.project.models;

public class Task {
    double taskTime;
    String name;
    String description;
    int subProjectId;
    int id;
    boolean isCompleted;
    int projectId;

    public Task(double taskTime,
                String name,
                String description,
                int subProjectId,
                int id,
                boolean isCompleted) {

        this.taskTime = taskTime;
        this.name = name;
        this.description = description;
        this.subProjectId = subProjectId;
        this.id = id;
        this.isCompleted = isCompleted;
        this.projectId = projectId;
    }

    public Task (String name, String description, int subProjectId) {
        this.name = name;
        this.description = description;
        this.subProjectId = subProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(double taskTime) {
        this.taskTime = taskTime;
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

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
