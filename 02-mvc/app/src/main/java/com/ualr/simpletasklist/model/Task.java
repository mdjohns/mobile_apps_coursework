package com.ualr.simpletasklist.model;

public class Task {
    private String taskDescription;
    private Boolean isComplete;

    public Task(String taskDescription, Boolean isComplete) {
        this.taskDescription = taskDescription;
        this.isComplete = isComplete;
    }


    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
