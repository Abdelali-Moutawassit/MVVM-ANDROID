package com.example.mvvmconcept.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<AuthUiState> authUiState = new MutableLiveData<>(new AuthUiState());

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<AuthUiState> getAuthUiState() {
        return authUiState;
    }

    public void login(String username, String password) {
        AuthUiState state = new AuthUiState();
        state.setLoading(true);
        authUiState.setValue(state);

        authRepository.login(username, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(String token) {
                AuthUiState successState = new AuthUiState();
                successState.setLoading(false);
                successState.setSuccess(true);
                authUiState.postValue(successState);
                // stocker token si besoin
            }

            @Override
            public void onError(String errorMessage) {
                AuthUiState errorState = new AuthUiState();
                errorState.setLoading(false);
                errorState.setSuccess(false);
                errorState.setErrorMessage(errorMessage);
                authUiState.postValue(errorState);
            }
        });
    }
}
