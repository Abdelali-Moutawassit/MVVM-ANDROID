package com.example.mvvmconcept.task;

import androidx.annotation.NonNull;

import com.example.mvvmconcept.core.network.RetrofitInstance;
import com.example.mvvmconcept.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {

    private final TaskApi taskApi;

    public interface TasksCallback {
        void onSuccess(List<Task> tasks);
        void onError(String message);
    }

    public TaskRepository() {
        taskApi = RetrofitInstance.getRetrofitInstance().create(TaskApi.class);
    }

    public void getTasks(final TasksCallback callback) {
        taskApi.getTasks().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Erreur lors de la récupération des tâches");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}