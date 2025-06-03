package com.example.mvvmconcept.task.list;

import com.example.mvvmconcept.model.Task;

import java.util.List;

public class TaskListUiState {

    private boolean isLoading;
    private List<Task> tasks;
    private String errorMessage;

    public TaskListUiState() {
        this.isLoading = false;
        this.tasks = null;
        this.errorMessage = null;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
