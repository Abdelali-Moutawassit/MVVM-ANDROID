package com.example.mvvmconcept.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/login")
    Call<AuthResponse> login(@Body AuthRequest request);

    class AuthRequest {
        private String username;
        private String password;

        public AuthRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }


    }

    class AuthResponse {
        private String token;
        private String message;

        public String getToken() {
            return token;
        }

        public String getMessage() {
            return message;
        }
    }
}
