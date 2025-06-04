package com.example.mvvmconcept.project.user;


import com.example.mvvmconcept.model.User;

import java.util.List;

public class UserListUiState {

    private boolean isLoading;

    private List<User> users;

    private String errorMessage;

    public UserListUiState() {
        this.isLoading = isLoading;
        this.users = users;
        this.errorMessage = errorMessage;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
