package kea.gruppe5.project.models;

public class Subtask {
    double time;
    String name;
    String description;
    int subtaskId;
    int taskId;
    boolean isCompleted;

    public Subtask(double time,
                   String name,
                   String description,
                   int subtaskId,
                   int taskId,
                   boolean isCompleted) {
        this.time = time;
        this.name = name;
        this.description = description;
        this.subtaskId = subtaskId;
        this.taskId = taskId;
        this.isCompleted = isCompleted;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
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

    public int getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(int id) {
        this.subtaskId = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
