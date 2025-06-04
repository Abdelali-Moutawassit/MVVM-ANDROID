package com.example.mvvmconcept.project.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmconcept.model.User;
import com.example.mvvmconcept.project.net.ApiClient;
import com.example.mvvmconcept.project.net.ApiService;
import com.example.mvvmconcept.task.list.TaskListUiState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersViewModel extends ViewModel {

    ApiService apiService = ApiClient.getApiService();

    private final MutableLiveData<UserListUiState> userListUiState = new MutableLiveData<>(new UserListUiState());

    public UsersViewModel(){
        loadUsers();
    }

    private void loadUsers() {
        UserListUiState loadingState = new UserListUiState();
        loadingState.setLoading(true);
        userListUiState.setValue(loadingState);

        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                UserListUiState successState = new UserListUiState();
                successState.setLoading(false);
                successState.setUsers(response.body());
                userListUiState.postValue(successState);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                UserListUiState errorState = new UserListUiState();
                errorState.setLoading(false);
                errorState.setErrorMessage(t.getMessage());
                userListUiState.postValue(errorState);
            }
        });

    }

    public LiveData<UserListUiState> getUserListUiState() {
        return userListUiState;
    }
}
