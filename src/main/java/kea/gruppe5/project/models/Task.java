package kea.gruppe5.project.models;

public class Task {
    double taskTime;
    String name;
    String description;
    String subTaskList;
    int subProjectId;
    int id;
    boolean isCompleted;

    public Task(double taskTime,
                String name,
                String description,
                String subTaskList,
                int subProjectId,
                int id,
                boolean isCompleted) {

        this.taskTime = taskTime;
        this.name = name;
        this.description = description;
        this.subTaskList = subTaskList;
        this.subProjectId = subProjectId;
        this.id = id;
        this.isCompleted = isCompleted;
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

    public String getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(String subTaskList) {
        this.subTaskList = subTaskList;
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
