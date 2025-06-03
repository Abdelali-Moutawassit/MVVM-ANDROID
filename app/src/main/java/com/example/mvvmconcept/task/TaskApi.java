package com.example.mvvmconcept.task;

import com.example.mvvmconcept.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TaskApi {

    @GET("/todos")
    Call<List<Task>> getTasks();

    @GET("/todos/{id}")
    Call<Task> getTaskById(@Path("id") int id);
}
