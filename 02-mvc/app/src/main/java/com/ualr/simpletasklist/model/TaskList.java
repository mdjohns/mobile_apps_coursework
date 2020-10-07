package com.ualr.simpletasklist.model;

import java.util.HashMap;


public class TaskList {
    private HashMap<Integer, Task> tasks;

    public TaskList() {
        this.tasks = new HashMap<>();
    }

    public HashMap<Integer, Task> getTasks() {
        return tasks;
    }

    public void setTasks(HashMap<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public void add(String description) {
        Integer taskId = tasks.size() + 1;
        Task newTask = new Task(description, false);

        tasks.put(taskId, newTask);
    }

    public void delete(Integer taskId) throws Exception {
        try {
            Task deletedTask = this.getTasks().remove(taskId);

            if (deletedTask == null) {
                throw new Exception("Invalid task ID");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    public void markDone(Integer taskId) throws Exception {
        try {
            Task completedTask = this.getTasks().get(taskId);

            if (completedTask != null) {
                completedTask.setComplete(true);
            }
            else {
                throw new Exception("Invalid task ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (HashMap.Entry<Integer, Task> task: tasks.entrySet()) {
            output
                .append(task.getKey().toString())
                .append("\t")
                .append(task.getValue().getTaskDescription())
                .append("\t");

            if (task.getValue().getComplete()) {
                output.append("\t Done");
            }
            output.append("\n");
        }

        return output.toString();
    }


}
