package com.example.mvvmconcept.task.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmconcept.model.Task;
import com.example.mvvmconcept.task.TaskApi;
import com.example.mvvmconcept.core.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailViewModel extends ViewModel {

    private final MutableLiveData<Task> task = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    private final TaskApi taskApi;

    public TaskDetailViewModel() {
        taskApi = RetrofitInstance.getRetrofitInstance().create(TaskApi.class);
    }

    public LiveData<Task> getTask() {
        return task;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchTaskById(int taskId) {
        isLoading.setValue(true);
        errorMessage.setValue(null);

        taskApi.getTaskById(taskId).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    task.setValue(response.body());
                } else {
                    errorMessage.setValue("Erreur de chargement de la tâche");
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Erreur : " + t.getMessage());
            }
        });
    }
}
