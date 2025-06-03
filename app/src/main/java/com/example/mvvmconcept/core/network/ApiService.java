package com.example.mvvmconcept.core.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.mvvmconcept.model.Task;
import com.example.mvvmconcept.model.User;

/**
 * Interface Retrofit définissant les endpoints de l'API.
 * Exemple simple avec deux endpoints GET pour récupérer des tâches et un utilisateur.
 */
public interface ApiService {

    @GET("tasks")
    Call<List<Task>> getTasks();

    @GET("tasks/{id}")
    Call<Task> getTaskById(@Path("id") int taskId);

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") int userId);

    // Ajouter d'autres endpoints selon besoin
}
