package com.example.mvvmconcept.ui.user;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmconcept.data.api.ApiClient;
import com.example.mvvmconcept.data.api.ApiService;
import com.example.mvvmconcept.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<List<User>> users = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<Boolean> getLoadingState() {
        return isLoading;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void fetchUsers() {
        isLoading.setValue(true);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    users.setValue(response.body());
                } else {
                    error.setValue("Erreur serveur : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                isLoading.setValue(false);
                error.setValue("Erreur réseau : " + t.getMessage());
            }
        });
    }
}
