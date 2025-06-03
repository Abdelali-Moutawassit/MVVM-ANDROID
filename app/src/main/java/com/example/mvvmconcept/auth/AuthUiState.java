package com.example.mvvmconcept.auth;

public class AuthUiState {
    private boolean isLoading;
    private boolean isSuccess;
    private String errorMessage;

    public AuthUiState() {
        this.isLoading = false;
        this.isSuccess = false;
        this.errorMessage = null;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
