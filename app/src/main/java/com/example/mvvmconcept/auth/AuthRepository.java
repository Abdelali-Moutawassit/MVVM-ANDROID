package com.example.mvvmconcept.auth;

import androidx.annotation.NonNull;

import com.example.mvvmconcept.core.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private AuthApi authApi;

    public AuthRepository() {
        authApi = RetrofitInstance.getRetrofitInstance().create(AuthApi.class);
    }

    public interface AuthCallback {
        void onSuccess(String token);
        void onError(String errorMessage);
    }

    public void login(String username, String password, AuthCallback callback) {
        AuthApi.AuthRequest request = new AuthApi.AuthRequest(username, password);

        authApi.login(request).enqueue(new Callback<AuthApi.AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthApi.AuthResponse> call, @NonNull Response<AuthApi.AuthResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getToken());
                } else {
                    callback.onError("Login failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthApi.AuthResponse> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
