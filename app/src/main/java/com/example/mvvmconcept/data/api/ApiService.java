package com.example.mvvmconcept.data.api;

import com.example.mvvmconcept.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
