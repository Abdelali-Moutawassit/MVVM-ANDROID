package com.example.mvvmconcept.project.net;


import com.example.mvvmconcept.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();
}
